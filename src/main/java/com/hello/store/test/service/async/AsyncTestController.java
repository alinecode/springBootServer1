package com.hello.store.test.service.async;

import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.store.test.web.Data;
import com.hello.store.test.web.Rtn;

@RestController
@RequestMapping("/async")
public class AsyncTestController {

	@Autowired
	AsyncServiceImpl asyncServiceImpl;
	
	@RequestMapping(value = "/test1")
	public Data testname(String aa , HttpServletRequest request ,Rtn<String> rtn) throws Exception {
		
//		System.out.println(aa);
//		System.err.println("--------当前线程--------"+Thread.currentThread().getName());
		
		Future<String> run = asyncServiceImpl.run(aa);
		
		System.err.println(run);
		
		if (StringUtils.isNotBlank(aa)) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("aa", aa);
			return  rtn.success(run.get());
		}
		
		return Rtn.error("值为空");
		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}
	
}
