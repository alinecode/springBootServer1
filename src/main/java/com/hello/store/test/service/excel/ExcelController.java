package com.hello.store.test.service.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("excel")
public class ExcelController {
	
//	@Autowired
//	ExcelService excelService;

	
	/**
	 * 写excel方式一
	 * @param request
	 * @param response
	 * @return json数据
	 */
	@RequestMapping("writeExcel")
	public String writeExcel(HttpServletRequest request,HttpServletResponse response) {
		
		// 获取值
		// 在service处理
		// 返回json格式数据，由前端处理导出
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("表头1", "表头2");
		jsonObject.put("666", "777");
		
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 导入excel方式一
	 * @param request
	 * @param response
	 * @return json数据
	 */
	@RequestMapping("importExcel")
	public String importExcel(@RequestBody JSONArray jsonArray,HttpServletRequest request,HttpServletResponse response) {
		
		System.err.println(jsonArray);
		
		// 获取值
		// 在service处理
		// 返回json格式数据，由前端处理导出
		
		return jsonArray.toJSONString();
	}
	
	
	
}
