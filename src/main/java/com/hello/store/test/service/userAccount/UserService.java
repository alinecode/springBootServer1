package com.hello.store.test.service.userAccount;

import com.hello.store.test.dto.UserAccountDto;

public interface UserService {

	String addUser(UserAccountDto accountDto);
	
	
	String login(UserAccountDto accountDto);


	String chatInit(UserAccountDto user);


	UserAccountDto queryUserByUsername(String username);
}
