package com.hello.store.test.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.core.kit.StringKit;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MDCodeGen;

import com.hello.store.test.gen.genImpl.ControllerCodeGen;
import com.hello.store.test.gen.genImpl.DaoCodeGen;
import com.hello.store.test.gen.genImpl.DtoCodeGen;
import com.hello.store.test.gen.genImpl.ServiceCodeGen;
import com.hello.store.test.gen.genImpl.ServiceImplCodeGen;

/**
 * 根据表生成service等代码。
 * 使用方式：往Set<String> tables中add要生成的表名称。
 * 新项目中使用本代码前提：
 * 1、在配置文件加上如下代码：↓。重要提示：beetlsql.sqlPath改成自己生成的md文件目录，在同一目录下如果有重名就会把原文件覆盖掉。
	beetlsql.daoSuffix=Dao
	beetlsql.basePackage=com
	beetlsql.baseDataSource.dbStyle=org.beetl.sql.core.db.MySqlStyle
	beetlsql.sqlPath=/beetlsql
	beetlsql.nameConversion=org.beetl.sql.core.UnderlinedNameConversion
 * 2、maven中有beetl-framework-starter 1.2.35版本或者以上版本的引用。如果低于该版本，有的方法可能无法使用。
 * 3、在Java project的resource下面新建beetlsql文件夹，用来存放生成的SQL md文件。在那再新建一个文件夹beetlsqlTemplate用来存放模板文件。
 * 4、把本项目的模板文件复制进你的的项目。自行修改模板文件为合适的。
 * 5、运行生成后刷新项目代码即可以看到生成的代码。
 * 
 * @author AL
 *
 */
public class BackendMainCodeGen {

	// ========数据库配置=========
	private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/books?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=CTT";
//	private static String url = "jdbc:mysql://127.0.0.1:3306/qiqiim?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	private static String url = "jdbc:mysql://192.168.9.120:3306/dst_ncwz?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	private static String userName = "root";
    private static String password = "123456";
//	private static String password = "1234";
	// ========模板的路径, 示例是spring boot的[src/main/resources/beetlsqlTemplate
	// 文件夹]=========
	private static String templatePath = "/beetlsqlTemplate";
	// ========md生成路径 要提前创建=========
	private static String mdPath = "/beetlsql";
	// ========生成实体类所在的包=========
//	private static String pojoPkg = "com.hello.store.test.genTest.model";
	private static String pojoPkg = "com.disiteng.framework.model";
	// ========生成实体类所在的包=========
//	private static String dtoPkg = "com.hello.store.test.genTest.dto";
	private static String dtoPkg = "com.disiteng.framework.dto";
	// ========生成mapper类所在的包=========
//	private static String daoPkg = "com.hello.store.test.genTest.dao";
	private static String daoPkg = "com.disiteng.framework.dao";
	// ========生成service类所在的包=========
//	private static String servicePkg = "com.hello.store.test.genTest.service";
	private static String servicePkg = "com.disiteng.framework.service";
	// ========生成controller类所在的包=========
//	private static String controllerPkg = "com.hello.store.test.genTest.service";
	private static String controllerPkg = "com.disiteng.framework.service";

	/**
	 * 入口
	 */
	public static void main(String[] args) throws Exception {
		genAll();
	}

	public static void genAll() throws Exception {
		// 准备工作
		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		DBStyle mysql = new MySqlStyle();
		SQLLoader loader = new ClasspathLoader(mdPath);
		UnderlinedNameConversion nc = new UnderlinedNameConversion();
		SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, null);

		GenConfig config = new GenConfig();
		config.setDisplay(false);
		config.setPreferBigDecimal(true);
		config.setTemplate(config.getTemplate(templatePath + "/pojoDto.btl"));

		System.out.println("======生成代码======");
//        Set<String> tables = sqlManager.getMetaDataManager().allTable(); // 生成所有表代码
		Set<String> tables = new HashSet<>();
		tables.add("yj05");
		tables.add("yj06");

		for (String table : tables) {
			System.out.printf("%-20s %s\n", table, "生成完毕");
			if ("user_info".equals(table)) {
				System.out.printf("请修改成你需要生成的表");
				return;
			}
			// 使用默认方法生成pojo
			sqlManager.genPojoCode(table, pojoPkg);
			// 使用自定义方法生成dto
			genDtoCode(sqlManager, config, table);
			// 生成service接口：↓(也可以放到genPojoCode方法的参数中，
			// 使用方法参见：https://javamonkey.github.io/guide/beetlsql.html 20.2. 生成更多的代码以及ext下面几个类的源码)
			genService(sqlManager, config, table);
			// 生成md
            genMd(sqlManager, config, table);
			// 生成dao代码
            daoCodeGen(sqlManager, config, table);
            // 生成service实现类
            genServiceImpl(sqlManager, config, table);
            // 生成controller实现类
            genController(sqlManager, config, table);

		}
		System.out.println("=====生成完毕=====");
	}

	public static void genController(SQLManager sqlManager, GenConfig config, String table) {
		String className = sqlManager.getNc().getClassName(table);
		ControllerCodeGen serviceCodeGen = new ControllerCodeGen(controllerPkg+"."+StringKit.toLowerCaseFirstOne(className));// 指定包名
		serviceCodeGen.setMapperTemplate(config.getTemplate(templatePath + "/controller.btl")); // 指定模板
		serviceCodeGen.genCode(pojoPkg, className,null, null, false, dtoPkg, daoPkg,servicePkg+"."+StringKit.toLowerCaseFirstOne(className)); // 开始生成。参数依次为包名、类名、表结构
	}
	
	public static void genServiceImpl(SQLManager sqlManager, GenConfig config, String table) {
		String className = sqlManager.getNc().getClassName(table);
		ServiceImplCodeGen serviceCodeGen = new ServiceImplCodeGen(servicePkg+"."+StringKit.toLowerCaseFirstOne(className));// 指定包名
		serviceCodeGen.setMapperTemplate(config.getTemplate(templatePath + "/serviceImpl.btl")); // 指定模板
		serviceCodeGen.genCode(pojoPkg, className,null, null, false, dtoPkg, daoPkg); // 开始生成。参数依次为包名、类名、表结构
	}
	
	
	/**
	 * 生成md文件
	 */
    public static void genMd(SQLManager sqlManager, GenConfig config, String table) throws IOException {
        String fileName = StringKit.toLowerCaseFirstOne(sqlManager.getNc().getClassName(table));
        if (config.getIgnorePrefix() != null && !config.getIgnorePrefix().trim().equals("")) {
            fileName = fileName.replaceFirst(StringKit.toLowerCaseFirstOne(config.getIgnorePrefix()), "");
            fileName = StringKit.toLowerCaseFirstOne(fileName);
        }
        String target = GenKit.getJavaResourcePath() + "/" + mdPath + "/" + fileName + ".md";
        TableDesc desc = sqlManager.getMetaDataManager().getTable(table);
        FileWriter writer = new FileWriter(new File(target));
        MDCodeGen mdCodeGen = new MDCodeGen();
        mdCodeGen.setMapperTemplate(config.getTemplate(templatePath + "/md.btl"));
        mdCodeGen.genCode(sqlManager.getBeetl(), desc, sqlManager.getNc(), null, writer);
        writer.close();
    }
    
    public static void daoCodeGen(SQLManager sqlManager, GenConfig config, String table) {
    	DaoCodeGen codeGen = new DaoCodeGen(daoPkg);
    	codeGen.genCode(pojoPkg, sqlManager.getNc().getClassName(table), null, null, false);
	}
    
	/**
	 * 生成dto文件
	 * @param sqlManager
	 * @param config
	 * @param table
	 */
	public static void genDtoCode(SQLManager sqlManager, GenConfig config, String table) {
		config.setTemplate(config.getTemplate(templatePath + "/pojoDto.btl"));
		DtoCodeGen dtoCodeGen = new DtoCodeGen();
		dtoCodeGen.genCode(sqlManager, table, dtoPkg, GenKit.getJavaSRCPath(), config,sqlManager.getNc().getClassName(table)+"Dto");
	}
	
	/**
	 * 生成service文件
	 */
	public static void genService(SQLManager sqlManager, GenConfig config, String table) {
		String className = sqlManager.getNc().getClassName(table);
		ServiceCodeGen serviceCodeGen = new ServiceCodeGen(servicePkg+"."+StringKit.toLowerCaseFirstOne(className));// 指定包名
		serviceCodeGen.setMapperTemplate(config.getTemplate(templatePath + "/service.btl")); // 指定模板
		serviceCodeGen.genCode(dtoPkg, className,null, null, false); // 开始生成。参数依次为包名、类名、表结构
//		serviceCodeGen.genCode(dtoPkg, sqlManager.getNc().getClassName(table),
//				sqlManager.getMetaDataManager().getTable(table), null, false); // 开始生成。参数依次为包名、类名、表结构
	}


	/**
	 * 生成dao文件
	 */
//	public static void genMapper(SQLManager sqlManager, GenConfig config, String table) {
//		MapperCodeGen mapperCodeGen = new MapperCodeGen(mapperPkg);
//		mapperCodeGen.setMapperTemplate(config.getTemplate(templatePath + "/mapper.btl"));
//		mapperCodeGen.genCode(pojoPkg, sqlManager.getNc().getClassName(table),
//				sqlManager.getMetaDataManager().getTable(table), null, false);
//	}
	
}
