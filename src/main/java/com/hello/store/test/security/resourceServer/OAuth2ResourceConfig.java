package com.hello.store.test.security.resourceServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint ;

//    @Bean
//    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
//        return new CustomLogoutSuccessHandler();
//    } ;

     
    /**
     *    这里设置需要token验证的url
     *    这些url可以在WebSecurityConfigurerAdapter中排除掉，
     *    对于相同的url，如果二者都配置了验证
     *    则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
     *   WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     *
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and().requestMatchers().antMatchers("/**")
            .and().authorizeRequests().antMatchers("/**").authenticated();
    }
}
