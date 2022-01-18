package com.hello.store.test.gen;

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
import org.beetl.sql.ext.gen.GenConfig;

import com.hello.store.test.gen.cof.GenConfigEdit;
import com.hello.store.test.gen.frontendGenImpl.SourceGenEditedFrontList;

/**
 * 生成前端代码。
 * @author AL
 *
 */
public class FrontendCodeGen {

	// ========数据库配置=========
	private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/books?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=CTT";
	private static String url = "jdbc:mysql://192.168.9.120:3306/dst_ncwz?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	private static String userName = "root";
    private static String password = "123456";
	// ========md生成路径 要提前创建=========
	private static String mdPath = "/beetlsql";
	// 模板的位置
	private static String templatePath = "/beetlsqlTemplate";
	// 生成的前端文件存放位置
	private static String fronttemplatePath = "D:\\Users\\MACHENIKE\\code2\\nanchongjywz\\02_Development\\02_Front\\01_SourceCode\\scfx-manage-pro-ys\\src";
	// 子目录
	private static String fronttemplatePathSub = "pages\\aa";
	
	public static void main(String[] args) throws Exception {
		genAll();
	}

	private static void genAll() {

		// 准备工作
		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		DBStyle mysql = new MySqlStyle();
		SQLLoader loader = new ClasspathLoader(mdPath);
		UnderlinedNameConversion nc = new UnderlinedNameConversion();
		SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, null);

		GenConfig config = new GenConfig();
		config.setDisplay(false);
		config.setPreferBigDecimal(true);
//		config.setTemplate(config.getTemplate(templatePath + "/pojoDto.btl"));

		System.out.println("======生成前端代码======");
//        Set<String> tables = sqlManager.getMetaDataManager().allTable(); // 生成所有表代码
		Set<String> tables = new HashSet<>();
		tables.add("yw09");
		for (String table : tables) {
			System.out.printf("%-20s %s\n", table, "生成完毕");
			genList(sqlManager, config, table);
		}
		System.out.println("=====生成前端完毕=====");
		
	}
	
	/**
	 * 生成service文件
	 */
	public static void genList(SQLManager sqlManager, GenConfig config, String table) {
		GenConfigEdit genConfigEdit = new GenConfigEdit();
		String className = sqlManager.getNc().getClassName(table); 
		SourceGenEditedFrontList editedFrontList = new SourceGenEditedFrontList(fronttemplatePath, fronttemplatePathSub);
		editedFrontList.setMapperTemplate(genConfigEdit.getTemplate(templatePath + "/frontList.btl")); // 指定模板
		TableDesc desc = sqlManager.getMetaDataManager().getTable(table);
		editedFrontList.genCode(null, className, desc, genConfigEdit, false, null, null, null, sqlManager);
//		serviceCodeGen.genCode(dtoPkg, sqlManager.getNc().getClassName(table),
//				sqlManager.getMetaDataManager().getTable(table), null, false); // 开始生成。参数依次为包名、类名、表结构
	}

}
