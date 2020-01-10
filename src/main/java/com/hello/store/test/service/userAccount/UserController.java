package com.hello.store.test.service.userAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hello.store.test.dto.UserAccountDto;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/chatInit")
	public String chatInit(UserAccountDto user,HttpServletRequest request) {
        
        if (StringUtils.isEmpty(user.getAccount())) {
        	return Boolean.FALSE.toString();
		}
        String string = userService.chatInit(user);
        
		return string;
	}
	
	@RequestMapping(value = "/login")
	@ApiOperation(value = "登陆")
	public String login(@RequestBody UserAccountDto user,HttpServletRequest request) {
        
		// 更换数据库后
		if (2>1) {
//			return JSON.toJSONString(user);
			return Boolean.TRUE.toString();
		}
		
        if (StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword())) {
        	return Boolean.FALSE.toString();
		}
        String string = userService.login(user);
        
        if (string.equals("0")) {
        	return Boolean.FALSE.toString();
		}
        
		return Boolean.TRUE.toString();
	}
	
	@RequestMapping(value = "/register")
	public Boolean register(@RequestBody UserAccountDto user,HttpServletRequest request) {
		
		if (2>1) {
			return Boolean.FALSE;
		}
		
		System.err.println("用户名" + user.getAccount());
		System.err.println("用户密码" + user.getPassword());
		
		if (StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword())) {
			return Boolean.FALSE;
		}
		
		String flag = userService.addUser(user);
		
		if ("1".equals(flag)) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
		
		
	}
}
