package com.hello.store.test.security.authServer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hello.store.test.dto.UserAccountDto;
import com.hello.store.test.service.userAccount.UserService;

/**
 * 
 * @author 
 *
 */
@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAccountDto user = userService.queryUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        // 如果在测试、搭建阶段，数据库是明文密码，那么此处应该将密码加密一次，否则无法授权
        return new org.springframework.security.core.userdetails.User(user.getAccount(), 
        		new BCryptPasswordEncoder().encode(user.getPassword()), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
		

    /**
     * 实现UserDetailsService中的loadUserByUsername方法，用于加载用户数据
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = userService.queryUserByUsername(username);
////        if (user == null) {
////            throw new UsernameNotFoundException("用户不存在");
////        }
//
//        //用户权限列表
//        Collection<? extends GrantedAuthority> authorities = userService.queryUserAuthorities("");
//
//        return new AuthUser(
//                user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                authorities);
//    }
}

