package com.hello.store.test.service.mongodb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hello.store.test.entity.Test1;
import com.hello.store.test.mongodb.Test1Repository;

@RestController
@RequestMapping("/mon")
public class MonController {

	@Autowired
	Test1Repository test1Repository;

	@RequestMapping(value = "/test1")
	public String test1(HttpServletRequest request) {

		try {

			List<Test1> findByNameLike = test1Repository.findByNameLike("明");
			
			String reString = JSON.toJSONString(findByNameLike);

			return reString;
		} catch (Exception e) {
			return e.getLocalizedMessage();
			// TODO: handle exception
		}

	}

}
