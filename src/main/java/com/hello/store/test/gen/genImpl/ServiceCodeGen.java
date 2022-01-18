package com.hello.store.test.gen.genImpl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Template;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.core.kit.GenKit;
import org.beetl.sql.ext.gen.CodeGen;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.SourceGen;

/**
 * 
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * @author AL
 *
 */
public class ServiceCodeGen implements CodeGen {
	String pkg = null;
	private String mapperTemplate = "";

	public ServiceCodeGen() {
		mapperTemplate = new GenConfig().getTemplate("/beetlsqlTemplate/service.btl");

	}

	public ServiceCodeGen(String pkg) {
		this();
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
			boolean isDisplay) {
		if (pkg == null) {
			pkg = entityPkg;
		}
		Template template = SourceGen.getGt().getTemplate(mapperTemplate);
		String entityDto = entityClass+"Dto";
		String serviceName = entityClass+"Service";
		template.binding("className", serviceName);
		template.binding("package", pkg);
//		template.binding("entityClass", mapperClass);
		template.binding("entityDto", entityDto);
		String lowEntityDto = StringUtils.replace(entityDto, entityDto.substring(0, 1), entityDto.substring(0, 1).toLowerCase(), 1);
		template.binding("lowEntityDto",lowEntityDto);
//		String mapperHead = "import " + entityPkg + ".*;" + SourceGen.CR;
		String mapperHead = "import " + entityPkg + "." + entityDto + ";";
		template.binding("imports", mapperHead);
		String mapperCode = template.render();
		if (isDisplay) {
			System.out.println(mapperCode);
		} else {
			try {
				SourceGenEdited.saveSourceFile(GenKit.getJavaSRCPath(), pkg, serviceName, mapperCode);
			} catch (IOException e) {
				throw new RuntimeException("Service代码生成失败", e);
			}
		}

	}

}

