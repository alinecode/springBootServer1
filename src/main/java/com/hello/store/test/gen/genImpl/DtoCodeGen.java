package com.hello.store.test.gen.genImpl;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.gen.GenConfig;

/**
 * 使用SourceGenEdited
 * @author AL
 *
 */
public class DtoCodeGen {

	/**
	 * @param sm
	 * @param table 表名
	 * @param pkg 代码存放包名
	 * @param srcPath 一般是class path ，使用GenKit.getJavaSRCPath()获取。
	 * @param config 主要用于设置模板和一些配置偏好配置，必须new一个，必须设置模板否则会使用默认的生成pojo的模板
	 */
	public void genCode(SQLManager sm, String table, String pkg, String srcPath, GenConfig config,String className) {
		
		SourceGenEdited sourceGen = new SourceGenEdited(sm, table, pkg, srcPath, config,className);
		try {
			sourceGen.gen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
