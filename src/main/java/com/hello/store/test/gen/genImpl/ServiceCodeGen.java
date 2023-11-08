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
 * 模板中已有内容
 * 
 * {
    "package": "类所在包名文字，可能以小数点分割。（是new本类的时候传的，传的时候组装而成）",
    "imports": "引用的dto、pojo的类型的引用文字。",
    "className": "serviceName。传入的pojo类名组装而成。（调用本方法时，传入的）",
    "entityClass": "pojo类名。调用时传的",
    "entityDto": "Dto名字，由entityClass组装而成。",
    "lowEntityDto": "dto属性名称，Dto第一个字母小写而来。"
}
 * 
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * @author AL
 *
 */
public class ServiceCodeGen {
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
	 * @param dtoPkg 实体类的包名
	 * @param entityClass 实体类名称
	 * @param tableDesc 
	 * @param config
	 * @param isDisplay
	 * @param pojoPkg 
	 */
	public void genCode(String dtoPkg, String entityClass, TableDesc tableDesc, GenConfig config,
			boolean isDisplay, String pojoPkg) {
		if (pkg == null) {
			pkg = dtoPkg;
		}
		Template template = SourceGen.getGt().getTemplate(mapperTemplate);
		String entityDto = entityClass+"Dto";
		String serviceName = entityClass+"Service";
		template.binding("className", serviceName);
		template.binding("package", pkg);
		template.binding("entityClass", entityClass);
		template.binding("entityDto", entityDto);
		String lowEntityDto = StringUtils.replace(entityDto, entityDto.substring(0, 1), entityDto.substring(0, 1).toLowerCase(), 1);
		template.binding("lowEntityDto",lowEntityDto);
//		String mapperHead = "import " + entityPkg + ".*;" + SourceGen.CR;
		
//		String mapperHead = "import " + entityPkg + "." + entityDto + ";";
		StringBuffer t = new StringBuffer();
		t.append("import " + dtoPkg + "." + entityDto + ";");
		t.append(SourceGen.CR);
		t.append( "import " + pojoPkg + "." + entityClass + ";");
		t.append(SourceGen.CR);
		String importHead = t.toString();
		template.binding("imports", importHead);
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

