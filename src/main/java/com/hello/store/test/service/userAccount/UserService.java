package com.hello.store.test.service.userAccount;

import org.beetl.sql.core.engine.PageQuery;

import com.hello.store.test.dto.UserAccountDto;

public interface UserService {

	String addUser(UserAccountDto accountDto);
	
	String login(UserAccountDto accountDto);

	String chatInit(UserAccountDto user);

	UserAccountDto queryUserByUsername(String username);
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	PageQuery<UserAccountDto> pageList(PageQuery<UserAccountDto> pageQuery);
}
