package com.hello.store.test.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.store.test.dao.UserAccountDao;
import com.hello.store.test.dto.UserAccountDto;

@RestController
public class Hello {
	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello spring boot";
	}
	@RequestMapping(value = "/login")
	public Boolean login(@RequestBody UserAccountDto user,HttpServletRequest request) {
		
		System.out.printf("用户名" + user.getAccount());
        System.out.printf("用户密码" + user.getPassword());
		
		return Boolean.TRUE;
	}
}
