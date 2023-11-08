package com.hello.store.test.gen.frontendGenImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Template;
import org.beetl.sql.core.JavaType;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.ColDesc;
import org.beetl.sql.core.db.TableDesc;
import org.beetl.sql.ext.gen.SourceGen;

import com.hello.store.test.gen.cof.GenConfigEdit;
import com.hello.store.test.gen.genImpl.SourceGenEdited;

/**
 * 
 * 修改自：{@link org.beetl.sql.ext.gen.MapperCodeGen}
 * @author AL
 *	不使用StringUtils也可以使用beetl的StringKit.toLowerCaseFirstOne
 *
 */
public class SourceGenEditedFrontList {
	String frontDir = null;
	String pkg = null;
	private String aTemplate = "";

//	public SourceGenEditedFrontList() {
//		aTemplate = new GenConfigEdit().getTemplate("/beetlsqlTemplate/frontList.btl");
//	}
	
	public SourceGenEditedFrontList(@NotBlank String frontDir,String pkg) {
		aTemplate = new GenConfigEdit().getTemplate("/beetlsqlTemplate/frontList.btl");
//		this();
		this.pkg = pkg;
		this.frontDir = frontDir;
	}

	public String getMapperTemplate() {
		return aTemplate;
	}

	public void setMapperTemplate(String mapperTemplate) {
		this.aTemplate = mapperTemplate;
	}

	/**
	 * 
	 * @param entityPkg 实体类的包名
	 * @param entityClass 实体类名称
	 * @param tableDesc 
	 * @param config
	 * @param isDisplay
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void genCode(String entityPkg, String entityClass, TableDesc tableDesc, GenConfigEdit config,
			boolean isDisplay,String entityDtoPkg,String daoPkg,String servicePkg,SQLManager sm) {
//		if (pkg == null) {
//			pkg = entityPkg;
//		}
		Template template = SourceGen.getGt().getTemplate(aTemplate);
		String frontModelName = StringUtils.isBlank(tableDesc.getRemark())?entityClass:tableDesc.getRemark()+"查询";
//		String daoName = entityClass+"Dao";
//		String entityDto = entityClass+"Dto";
//		String serviceName = entityClass+"Service";
//		String serviceImplName = entityClass+"ServiceImpl";
//		template.binding("package", pkg);
		template.binding("frontModelName", frontModelName);
		template.binding("className", entityClass);
		
		Set<String> cols = tableDesc.getCols();
		List<Map> attrs = new ArrayList<Map>();
		
		List<Map> frontQueryField = new ArrayList<Map>();
		
		// 详情页面有三个通用字段不显示
		List<String> asList = Arrays.asList("orgid","aae011","aae036");
		
		for (String col : cols) {

			ColDesc desc = tableDesc.getColDesc(col);
			Map attr = new HashMap();
			attr.put("comment", desc.remark);
			String attrName = sm.getNc().getPropertyName(null, desc.colName);
			
			attr.put("name", attrName);
			String type = JavaType.getType(desc.sqlType, desc.size, desc.digit);
			if (config.isPreferBigDecimal() && type.equals("Double")) {
				type = "BigDecimal";
			}
			if (config.isPreferDate() && type.equals("Timestamp")) {
				type = "Date";
			}

			attr.put("type", type);
			
			// 主键不显示
			boolean isKey = tableDesc.getIdNames().contains(desc.colName);
			if (isKey) {
				continue;
			}
			
			// 控制LIST查询页中只显示前6个字段
			if ((!StringUtils.contains(entityClass, "DDetail")) && attrs.size()>=6) {
				break;
			}else {
				// 详情页面有三个通用字段不显示
				if (asList.contains(attrName)) {
					continue;
				}
				
			}
			
			attrs.add(attr);
			
			// 查询只显示前3个字段
			if (frontQueryField.size() < 3) {
				frontQueryField.add(attr);
			}
		}
		
		if (!frontQueryField.isEmpty()) {
			template.binding("frontQueryField", frontQueryField);
		}
		template.binding("attrs", attrs);
		
//		template.binding("serviceName", serviceName);
//		String serviceNameLower = StringUtils.replace(serviceName, serviceName.substring(0, 1), serviceName.substring(0, 1).toLowerCase(), 1);
//		template.binding("serviceNameLower", serviceNameLower);
//		template.binding("daoName", daoName);
//		String daoNameLower = StringUtils.replace(daoName, daoName.substring(0, 1), daoName.substring(0, 1).toLowerCase(), 1);
//		template.binding("daoNameLower", daoNameLower);
//		template.binding("entityDto", entityDto);
//		String lowEntityDto = StringUtils.replace(entityDto, entityDto.substring(0, 1), entityDto.substring(0, 1).toLowerCase(), 1);
//		template.binding("lowEntityDto",lowEntityDto);
//		template.binding("entityClass", entityClass);
//		String entityClassLow = StringUtils.replace(entityClass, entityClass.substring(0, 1), entityClass.substring(0, 1).toLowerCase(), 1);
//		template.binding("entityClassLow", entityClassLow);
		
//		StringBuffer t = new StringBuffer();
//		t.append("import " + servicePkg + "." + serviceName + ";");
//		t.append(SourceGen.CR);
//		t.append("import " + entityDtoPkg + "." + entityDto + ";");
//		t.append(SourceGen.CR);
//		t.append("import " + daoPkg + "." + daoName + ";");
//		t.append(SourceGen.CR);
//		String importHead = t.toString();
//		template.binding("imports", importHead);
		String renderCode = template.render();
		if (isDisplay) {
			System.out.println(renderCode);
		} else {
			try {
				SourceGenEdited.saveSourceFileFront(frontDir, pkg, entityClass, renderCode);
			} catch (IOException e) {
				throw new RuntimeException("前端代码生成失败", e);
			}
		}

	}

}

