package com.hello.store.test.gen.cof;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.beetl.sql.ext.gen.CodeGen;

/**
 * POJO代码生成配置
 */
public class GenConfigEdit {
	public static final int ORDER_BY_TYPE = 1;
	public static final int ORDER_BY_ORIGNAL = 2;
	//默认模板路径
	private final static String defaultTemplatePath = "/org/beetl/sql/ext/gen/pojo.btl";
	/**
	 * 同时生成其他代码，比如Mapper
	 */
	public List<CodeGen> codeGens = new ArrayList<CodeGen>();
	public String space = "    ";
	//基类，默认就是Object
	private String baseClass;
	//格式控制，4个隔空
	private int spaceCount = 4;
	// double 类型采用BigDecimal
	private boolean preferBigDecimal = true;
	//采用java.util.Date
	private boolean preferDate = true;
	//输出包名
	private String outputPackage = "com.test";
	private String encoding = "UTF-8";
	/**
	 * 模板
	 */
	private String template = null;
	/**
	 * 是否实现序列化
	 */
	private boolean implSerializable = false;
	/**
	 * 忽略表名前缀
	 */
	private String ignorePrefix = "";
	private boolean display = false;
	private int propertyOrder = ORDER_BY_TYPE;

	/**
	 * 使用默认模板
	 */
	public GenConfigEdit() {
		this(defaultTemplatePath);
	}

	/**
	 * 使用自定义模板
	 *
	 * @param templatePath
	 */
	public GenConfigEdit(String templatePath) {
		initTemplate(templatePath);
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getOutputPackage() {
		return outputPackage;
	}

	//对于数字，优先使用封装类型
	//	private boolean preferPrimitive = false ;

	public void setOutputPackage(String outputPackage) {
		this.outputPackage = outputPackage;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public GenConfigEdit preferBigDecimal(boolean prefer) {
		this.preferBigDecimal = prefer;
		return this;
	}

	public GenConfigEdit preferPrimitive(boolean primitive) {
		this.preferBigDecimal = primitive;
		return this;
	}

	public String getBaseClass() {
		return baseClass;
	}

	public GenConfigEdit setBaseClass(String baseClass) {
		this.baseClass = baseClass;
		return this;
	}

	public int getSpaceCount() {
		return spaceCount;
	}

	public void setSpaceCount(int spaceCount) {
		this.spaceCount = spaceCount;
	}

	public boolean isPreferBigDecimal() {
		return preferBigDecimal;
	}

	public void setPreferBigDecimal(boolean preferBigDecimal) {
		this.preferBigDecimal = preferBigDecimal;
	}

	public boolean isPreferDate() {
		return preferDate;
	}

	public void setPreferDate(boolean preferDate) {
		this.preferDate = preferDate;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public GenConfigEdit setSpace(int count) {
		this.spaceCount = count;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(" ");
		}
		space = sb.toString();
		return this;
	}

	public boolean isDisplay() {
		return display;
	}

	public GenConfigEdit setDisplay(boolean display) {
		this.display = display;
		return this;
	}

	/**
	 * 使用模板文件的classpath来初始化模板
	 *
	 * @param classPath
	 */
	private void initTemplate(String classPath) {
		template = getTemplate(classPath);
	}

	/**
	 * mapper 代码生成
	 *
	 * @param classPath
	 * @since 2.6.1
	 */

	public String getTemplate(String classPath) {
		try {
			//系统提供一个pojo模板
			InputStream ins = GenConfigEdit.class.getResourceAsStream(classPath);
			if (ins == null) {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				if (loader != null) {
					ins = loader.getResourceAsStream(classPath);
				}
			}
			if (ins == null) {
				throw new RuntimeException("未在classpath下找到Pojo模板文件 " + classPath);
			}
			InputStreamReader reader = new InputStreamReader(ins, this.encoding);
			try {
				//todo, 根据长度来，不过现在模板不可能超过8k
				char[] buffer = new char[1024 * 16];
				int len = reader.read(buffer);
				return new String(buffer, 0, len);
			} finally {
				reader.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public int getPropertyOrder() {
		return propertyOrder;
	}

	public void setPropertyOrder(int propertyOrder) {
		this.propertyOrder = propertyOrder;
	}

	public boolean isImplSerializable() {
		return implSerializable;
	}

	public void setImplSerializable(boolean implSerializable) {
		this.implSerializable = implSerializable;
	}

	public String getIgnorePrefix() {
		return ignorePrefix;
	}

	public void setIgnorePrefix(String ignorePrefix) {
		this.ignorePrefix = ignorePrefix;
	}
}
