package com.hello.store.test.service.userAccount;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.store.test.dto.UserAccountDto;
import com.hello.store.test.util.JWTRSAUtil;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/chatInit")
	public String chatInit(UserAccountDto user, HttpServletRequest request) {

		try {

			if (StringUtils.isEmpty(user.getAccount())) {
				return Boolean.FALSE.toString();
			}
			String string = userService.chatInit(user);
			return string;

		} catch (Exception e) {
			return e.getLocalizedMessage();
			// TODO: handle exception
		}

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/login")
	@ApiOperation(value = "登陆")
	public String login(@RequestBody UserAccountDto user, HttpServletRequest request) {

		// 更换数据库后
		if (2 > 1) {
//			return JSON.toJSONString(user);
			return Boolean.TRUE.toString();
		}

		if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
			return Boolean.FALSE.toString();
		}
		String string = userService.login(user);

		if (string.equals("0")) {
			return Boolean.FALSE.toString();
		}

		return Boolean.TRUE.toString();
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/register")
	public Boolean register(@RequestBody UserAccountDto user, HttpServletRequest request) {

		if (2 > 1) {
			return Boolean.FALSE;
		}

		System.err.println("用户名" + user.getAccount());
		System.err.println("用户密码" + user.getPassword());

		if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
			return Boolean.FALSE;
		}

		String flag = userService.addUser(user);

		if ("1".equals(flag)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}
	
	@RequestMapping(value = "/test")
	public String testname(String aa , HttpServletRequest request) throws Exception {

//		String header = request.getHeader("Authorization");
//		String token = StringUtils.substringAfter(header, "bearer ");

//	        Claims claims = Jwts.parser().setSigningKey("test-secret".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();

//		Claims claims = JWTRSAUtil.parseToken(token);
//
//		// 取出自定义字段
//		String organization = (String) claims.get("organization");
//		// 取出jwt默认放入的user_name
//		String user_name = (String) claims.get("user_name");

//		if (StringUtils.isNotBlank(organization)) {
//
//			return organization + "||" + user_name;
//		}
		if (StringUtils.isNotBlank(aa)) {
			
			return aa + "||" ;
		}

		return null;

	}

}
