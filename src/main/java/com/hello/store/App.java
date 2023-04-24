package com.hello.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@ServletComponentScan
@EnableSwagger2
@EnableScheduling
@EnableAsync
//@EnableLeafServer

@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.hello.store.test.service.rabbitMQ.*") }) // 排除某个包，不扫描
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 要打成war包就需要继承SpringBootServletInitializer，重写configure。
	 * 实测不加而且仅修改pom.xml的话,打成war包后在Tomcat启动后将无法正常访问。
	 * SpringBootServletInitializer中的注释也写着，打成war包才需要这个类。
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(App.class);
	};
}
