package com.hello.store.test.gen.genImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.beetl.sql.core.JavaType;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.ColDesc;
import org.beetl.sql.core.db.MetadataManager;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.ext.gen.CodeGen;
import org.beetl.sql.ext.gen.GenConfig;

/**
 * 
 * 目前被用于生成 Dto、excelData。还有前端代码使用了部分生成文件的方法
 * 
 * 模板中已有内容：
 * {
    "attrs": {
        "name": "java属性名称",
        "colName": "数据库字段名",
        "comment": "Java/数据库注释",
        "index": "数据库列的序号，从-1开始，现在只有excel导入有使用",
        "methodName": "属性的方法名称，配合模板左边的get/set组成方法",
        "isKey": "是否是数据库主键（可能有多个）",
        "type": "Java类型（有判断逻辑，比如Double成BigDecimal，Timestamp成Date）",
        "ExcelIgnore": "excel的实体类属性是否有@ExcelIgnore注解，也就是easyexcel忽略导入导出的字段"
    },
    "className": "传入的类名。（new本方法时，传入的，比如根据数据库表名获得的Java类名、Dto名字、excel的Data名字）",
    "table": "数据库表名称。（new的时候传进来的，去除了数据库名.表名的形式，只保留的表名称）",
    "pkg": "类所在包名文字，可能以小数点分割。也会被生成子文件夹。（也是new本类的时候传的，在调用方法最顶上定义的）",
    "imports": "在static中srcHead写死的一些内容（目前是），比如dto中可能有的date类型。",
    "comment": "数据库表注释（好像有点bug为空，TODO 还需要修复）",
    "ext": "基类（用的配置set的baseClass的文字，没有使用）",
    "catalog": "所属数据库名称/在beetl自带的模板中使用了，在自定义模板中没有使用",
    "implSerializable": "Java类是否需要实现Serializable，是set在配置类中的"
}
 * 
 * 
 * 通用代码生成器。已用于生成dto。
 * 修改自：{@link org.beetl.sql.ext.gen.SourceGen}
 */
public class SourceGenEdited {
	public static final String CR = System.getProperty("line.separator");
	/**
	 * logger
	 */
	public static String defaultPkg = "com.test";
	private static String srcHead;
	/**
	 * 代码生成的Beetl的GroupTemplate，与BeetSQL 不同
	 */
	private static GroupTemplate gt = null;

	static {
		Configuration conf = null;
		try {
			conf = Configuration.defaultConfiguration();
			conf.setStatementStart("<%");
			conf.setStatementEnd("%>");
		} catch (IOException e) {
			throw new RuntimeException("build defaultConfiguration error", e);
		}

		gt = new GroupTemplate(new StringTemplateResourceLoader(), conf);
		StringBuffer t = new StringBuffer();
		t.append("import java.math.*;");
		t.append(CR);
		t.append("import java.util.Date;");
		t.append(CR);
		t.append("import java.sql.Timestamp;");
		t.append(CR);
		t.append("import org.beetl.sql.core.annotatoin.Table;");
		t.append(CR);
		srcHead = t.toString();
	}

	private MetadataManager mm;
	private SQLManager sm;
	private String table;
	private String pkg;
	private String srcPath;
	private String className;
	private GenConfig config;

	public SourceGenEdited(SQLManager sm, String table, String pkg, String srcPath, GenConfig config,@NotBlank String className) {
		this.mm = sm.getMetaDataManager();
		this.sm = sm;
		this.table = table;
		this.pkg = pkg;
		this.srcPath = srcPath;
		this.config = config;
		this.className = className;
	}

	public static String getSrcHead() {
		return srcHead;
	}

	public static GroupTemplate getGt() {
		return gt;
	}

	public static void saveSourceFile(String srcPath, String pkg, String className, String content) throws IOException {
		String file = srcPath + File.separator + pkg.replace('.', File.separatorChar);
		File f = new File(file);
		if (!f.exists()) {
			boolean succ = f.mkdirs();
			if (!succ) {
				throw new IOException("创建文件夹失败 " + f);
			}
		}
		File target = new File(file, className + ".java");
		System.err.println(file);
		// 避免丢失代码。
//		if (target.exists()) {
//			File target2 = new File(file, className+"-"+UUID.randomUUID() + ".java");
//			target.renameTo(target2);
//		}
		
		FileWriter writer = new FileWriter(target);
		try {
			writer.write(content);
		} finally {
			writer.close();
		}
	}
	
	public static void saveSourceFileFront(String srcPath, String pkg, String className, String content) throws IOException {
		String file = srcPath + File.separator + pkg.replace('.', File.separatorChar);
		File f = new File(file);
		if (!f.exists()) {
			boolean succ = f.mkdirs();
			if (!succ) {
				throw new IOException("创建文件夹失败 " + f);
			}
		}
		File target = new File(file, className + ".vue");
		System.err.println(file);
		// 避免丢失代码。
//		if (target.exists()) {
//			File target2 = new File(file, className+"-"+UUID.randomUUID() + ".java");
//			target.renameTo(target2);
//		}
		
		FileWriter writer = new FileWriter(target);
		try {
			writer.write(content);
		} finally {
			writer.close();
		}
	}

	/**
	 * 生成代码
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gen() throws Exception {
		final TableDesc tableDesc = mm.getTable(table);
		String className = this.className;
//		String className = sm.getNc().getClassName(tableDesc.getName());
//		if (config.getIgnorePrefix() != null && !config.getIgnorePrefix().trim().equals("")) {
//			className = className.replaceFirst(StringKit.toUpperCaseFirstOne(config.getIgnorePrefix()), "");
//		}
		String ext = null;

		if (config.getBaseClass() != null) {
			ext = config.getBaseClass();
		}

		Set<String> cols = tableDesc.getCols();
		List<Map> attrs = new ArrayList<Map>();
		for (String col : cols) {

			ColDesc desc = tableDesc.getColDesc(col);
			Map attr = new HashMap();
			attr.put("colName", desc.colName); // 字段（列）名称
			attr.put("comment", desc.remark);
			attr.put("index", attrs.size()-1); // 把序号加上。正好可以利用新的List。一般情况下第一个字段是id，不会被导入所以减1
			String attrName = sm.getNc().getPropertyName(null, desc.colName);  // 获取Java属性名
			attr.put("name", attrName);
			attr.put("methodName", getMethodName(attrName)); // 属性的方法名称，配合模板左边的get/set组成方法

			boolean isKey = tableDesc.getIdNames().contains(desc.colName);
			attr.put("isKey", isKey);

			String type = JavaType.getType(desc.sqlType, desc.size, desc.digit);
			if (config.isPreferBigDecimal() && type.equals("Double")) {
				type = "BigDecimal";
			}
			if (config.isPreferDate() && type.equals("Timestamp")) {
				type = "Date";
			}

			attr.put("type", type);
			attr.put("desc", desc);
			
			// 三个通用字段不显示excel导入导出
			List<String> asList = Arrays.asList("orgid","aae011","aae036");
			
			// 除开pojo的代码，对生成的easyExcel的注解的忽略注解
			if (isKey || asList.contains(attrName)) {
				attr.put("ExcelIgnore", true);
			}else {
				attr.put("ExcelIgnore", false);
			}
			
			attrs.add(attr);
		}

//		if (config.getPropertyOrder() == GenConfig.ORDER_BY_TYPE) {
//			// 主键总是排在前面，int类型也排在前面，剩下的按照字母顺序排
//			Collections.sort(attrs, new Comparator<Map>() {
//
//				@Override
//				public int compare(Map o1, Map o2) {
//					ColDesc desc1 = (ColDesc) o1.get("desc");
//					ColDesc desc2 = (ColDesc) o2.get("desc");
//					int score1 = score(desc1);
//					int score2 = score(desc2);
//					if (score1 == score2) {
//						return desc1.colName.compareTo(desc2.colName);
//					} else {
//						return score2 - score1;
//					}
//
//
//				}
//
//				private int score(ColDesc desc) {
//					if (tableDesc.getIdNames().contains(desc.colName)) {
//						return 99;
//					} else if (JavaType.isInteger(desc.sqlType)) {
//						return 9;
//					} else if (JavaType.isDateType(desc.sqlType)) {
//						return -9;
//					} else {
//						return 0;
//					}
//				}
//
//
//			});
//		}


		Template template = gt.getTemplate(config.getTemplate());
		template.binding("attrs", attrs);
		template.binding("className", className);
		template.binding("table", trimCategory(table));
		template.binding("ext", ext);
		template.binding("package", pkg);
		template.binding("imports", srcHead);
		template.binding("comment", tableDesc.getRemark());
		template.binding("catalog", tableDesc.getCatalog());
		template.binding("implSerializable", config.isImplSerializable());

		String code = template.render();
		if (config.isDisplay()) {
			System.out.println(code);
		} else {
			saveSourceFile(srcPath, pkg, className, code);
		}


		for (CodeGen codeGen : config.codeGens) {
			codeGen.genCode(pkg, className, tableDesc, config, config.isDisplay());
		}


	}

	private String trimCategory(String table) {
		int index = -1;
		if ((index = table.indexOf(".")) == -1) {
			return table;
		}
		return table.substring(index + 1);
	}

	private String getMethodName(String name) {
		if (name.length() == 1) {
			return name.toUpperCase();
		}
		char ch1 = name.charAt(0);
		char ch2 = name.charAt(1);
		if (Character.isLowerCase(ch1) && Character.isUpperCase(ch2)) {
			//aUname---> getaUname();
			return name;
		} else if (Character.isUpperCase(ch1) && Character.isUpperCase(ch2)) {
			//ULR --> getURL();
			return name;
		} else {
			//general  name --> getName()
			char upper = Character.toUpperCase(ch1);
			return upper + name.substring(1);
		}
	}

}

