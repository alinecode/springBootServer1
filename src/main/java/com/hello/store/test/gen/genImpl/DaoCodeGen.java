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
 * new的时候传入包名。
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * @author AL
 *
 */
public class DaoCodeGen implements CodeGen {
	String pkg = null;
	private String daoTemplate = "";

	public DaoCodeGen() {
		daoTemplate = new GenConfig().getTemplate("/beetlsqlTemplate/dao.btl");

	}

	public DaoCodeGen(String pkg) {
		this();
		this.pkg = pkg;
	}

	public String getMapperTemplate() {
		return daoTemplate;
	}

	public void setMapperTemplate(String mapperTemplate) {
		this.daoTemplate = mapperTemplate;
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
		Template template = SourceGen.getGt().getTemplate(daoTemplate);
		String entity = entityClass; // 引用的bean基础类
		String daoName = entityClass+"Dao"; // 类名和文件名
		template.binding("className", daoName); 
		template.binding("package", pkg);
//		template.binding("entityClass", mapperClass);
		template.binding("entity", entity);
		String lowEntity = StringUtils.replace(entity, entity.substring(0, 1), entity.substring(0, 1).toLowerCase(), 1);
		template.binding("lowEntityDto", lowEntity);

//		String mapperHead = "import " + entityPkg + ".*;" + SourceGen.CR;
		String mapperHead = "import " + entityPkg + "." + entity + ";";
		template.binding("imports", mapperHead);
		String mapperCode = template.render();
		if (isDisplay) {
			System.out.println(mapperCode);
		} else {
			try {
				SourceGenEdited.saveSourceFile(GenKit.getJavaSRCPath(), pkg, daoName, mapperCode);
			} catch (IOException e) {
				throw new RuntimeException("dao代码生成失败", e);
			}
		}

	}

}

