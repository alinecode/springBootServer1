package com.hello.store.test.gen.genImpl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Template;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.SourceGen;

/**
 * 
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * @author AL
 *	不使用StringUtils也可以使用beetl的StringKit.toLowerCaseFirstOne
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
	 * @param entityPkg 实体类的包名
	 * @param entityClass 实体类名称
	 * @param tableDesc 
	 * @param config
	 * @param isDisplay
	 */
	public void genCode(String entityPkg, String entityClass, TableDesc tableDesc, GenConfig config,
			boolean isDisplay,String entityDtoPkg,String daoPkg) {
		if (pkg == null) {
			pkg = entityPkg;
		}
		Template template = SourceGen.getGt().getTemplate(mapperTemplate);
		String daoName = entityClass+"Dao";
		String entityDto = entityClass+"Dto";
		String serviceName = entityClass+"Service";
		String serviceImplName = entityClass+"ServiceImpl";
		template.binding("package", pkg);
		template.binding("className", serviceImplName);
		template.binding("serviceName", serviceName);
		template.binding("daoName", daoName);
		String daoNameLower = StringUtils.replace(daoName, daoName.substring(0, 1), daoName.substring(0, 1).toLowerCase(), 1);
		template.binding("daoNameLower", daoNameLower);
		template.binding("entityDto", entityDto);
		String lowEntityDto = StringUtils.replace(entityDto, entityDto.substring(0, 1), entityDto.substring(0, 1).toLowerCase(), 1);
		template.binding("lowEntityDto",lowEntityDto);
		template.binding("entityClass", entityClass);
		String entityClassLow = StringUtils.replace(entityClass, entityClass.substring(0, 1), entityClass.substring(0, 1).toLowerCase(), 1);
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

}

