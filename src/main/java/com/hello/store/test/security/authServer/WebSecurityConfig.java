package com.hello.store.test.security.authServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//    @Resource(name = "userService")
//    private UserDetailsService userDetailsService;
	
	/**
	 * AuthorizationServerConfigurerAdapter中Autowired使用
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 *
	 * 在此处不拦截oauth要开放的资源
	 *
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**").and().authorizeRequests()
				.antMatchers("/oauth/**").authenticated().and().formLogin().permitAll();
	}

	// 配置内存模式的用户
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("demoUser1").password(this.passwordEncoder().encode("123456"))
//				.authorities("USER").build());
//		manager.createUser(User.withUsername("demoUser2").password(this.passwordEncoder().encode("123456"))
//				.authorities("USER").build());
//		return manager;
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 加载时使用，可以加载内存式用户
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

}
