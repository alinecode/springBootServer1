package com.hello.store.test.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 笔记 http://note.youdao.com/s/QFopceZB
 * 注册webservice服务端。注册的意思就是交给spring管理，在项目启动时加载进内存，方便直接使用。
 * 文档： https://cxf.apache.org/docs/springboot.html 注意stater版本，官网的可能太低了不支持springboot2.0
 * 需要自己去maven仓库找更高版本的，比如本项目使用的3.2.5
 * @author 
 *
 */
@Configuration
public class WebServiceConfig {
	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new GreetWebServiceImpl());
		endpoint.publish("/aaaa");
		return endpoint;
	}
}