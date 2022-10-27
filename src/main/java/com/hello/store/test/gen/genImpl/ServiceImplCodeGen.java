package com.hello.store.test.gen.genImpl;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Template;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.SourceGen;

/**
 * 
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * 
 * @author AL 不使用StringUtils也可以使用beetl的StringKit.toLowerCaseFirstOne
 *
 */
public class ServiceImplCodeGen {
	String pkg = null;
	private String mapperTemplate = "";

//	public ServiceImplCodeGen() {
//
//	}
	public ServiceImplCodeGen(String pkg) {
//		this();
		mapperTemplate = new GenConfig().getTemplate("/beetlsqlTemplate/serviceImpl.btl");
		this.pkg = pkg;
	}

	public String getMapperTemplate() {
		return mapperTemplate;
	}

	public void setMapperTemplate(String mapperTemplate) {
		this.mapperTemplate = mapperTemplate;
	}

	/**
	 * 
	 * @param entityPkg   实体类的包名
	 * @param entityClass 实体类名称
	 * @param tableDesc
	 * @param config
	 * @param isDisplay
	 */
	public void genCode(String entityPkg, String entityClass, TableDesc tableDesc, GenConfig config, boolean isDisplay,
			String entityDtoPkg, String daoPkg, SQLManager sqlManager) {
		if (pkg == null) {
			pkg = entityPkg;
		}
		Template template = SourceGen.getGt().getTemplate(mapperTemplate);

		Set<String> idNames = tableDesc.getIdNames();
		// 如果有id，那么取出第一个id
		if (!idNames.isEmpty()) {
			String next = idNames.iterator().next(); // 数据库id字段
			template.binding("dbidName", next);
			String idNameProperty = sqlManager.getNc().getPropertyName(null, next); // Java类的id属性字段
			template.binding("idNameProperty", idNameProperty);
			String methodName = getMethodName(idNameProperty); // Java类的id属性字段首字母大写，方便组装get
			template.binding("methodName", methodName);
		}
		
		String tbname = tableDesc.getName(); 
		template.binding("tbname", tbname); // 表名

		String daoName = entityClass + "Dao";
		String entityDto = entityClass + "Dto";
		String serviceName = entityClass + "Service";
		String serviceImplName = entityClass + "ServiceImpl";
		template.binding("package", pkg);
		template.binding("className", serviceImplName);
		template.binding("serviceName", serviceName);
		template.binding("daoName", daoName);
		String daoNameLower = StringUtils.replace(daoName, daoName.substring(0, 1),
				daoName.substring(0, 1).toLowerCase(), 1);
		template.binding("daoNameLower", daoNameLower);
		template.binding("entityDto", entityDto);
		String lowEntityDto = StringUtils.replace(entityDto, entityDto.substring(0, 1),
				entityDto.substring(0, 1).toLowerCase(), 1);
		template.binding("lowEntityDto", lowEntityDto);
		template.binding("entityClass", entityClass);
		String entityClassLow = StringUtils.replace(entityClass, entityClass.substring(0, 1),
				entityClass.substring(0, 1).toLowerCase(), 1);
		template.binding("entityClassLow", entityClassLow);

		StringBuffer t = new StringBuffer();
		t.append("import " + entityPkg + "." + entityClass + ";");
		t.append(SourceGen.CR);
		t.append("import " + entityDtoPkg + "." + entityDto + ";");
		t.append(SourceGen.CR);
		t.append("import " + daoPkg + "." + daoName + ";");
		t.append(SourceGen.CR);
		String importHead = t.toString();
		template.binding("imports", importHead);
		String renderCode = template.render();
		if (isDisplay) {
			System.out.println(renderCode);
		} else {
			try {
				SourceGenEdited.saveSourceFile(GenKit.getJavaSRCPath(), pkg, serviceImplName, renderCode);
			} catch (IOException e) {
				throw new RuntimeException("serviceImpl代码生成失败", e);
			}
		}

	}

	private String getMethodName(String name) {
		if (name.length() == 1) {
			return name.toUpperCase();
		}
		char ch1 = name.charAt(0);
		char ch2 = name.charAt(1);
		if (Character.isLowerCase(ch1) && Character.isUpperCase(ch2)) {
			// aUname---> getaUname();
			return name;
		} else if (Character.isUpperCase(ch1) && Character.isUpperCase(ch2)) {
			// ULR --> getURL();
			return name;
		} else {
			// general name --> getName()
			char upper = Character.toUpperCase(ch1);
			return upper + name.substring(1);
		}
	}

}
