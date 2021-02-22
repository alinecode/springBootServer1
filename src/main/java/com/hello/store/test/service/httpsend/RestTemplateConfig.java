package com.hello.store.test.service.httpsend;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * 其他说明：springboot所包含的http请求完整代码都在这个包下面，不难理解。
 * 
 * 该类说明：让RestTemplate在使用时可以@Autowired
 * 
 * 参考：
 * https://www.tutorialspoint.com/spring_boot/spring_boot_rest_template.htm
 * https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
 * @author 
 *
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
