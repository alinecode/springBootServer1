package com.hello.store.test.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * 笔记以及客户端测试过程： http://note.youdao.com/s/QFopceZB
 * 服务端调用
 * @author 
 *
 */
public class GreetingClintTest {

	public static void main(String[] args) {
		
	    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(GreetWebService.class);
        factory.setAddress("http://192.168.9.81:9999/store-service/services/aaaa?wsdl");
        GreetWebService client = (GreetWebService) factory.create();
        String result = client.greeting(" David ");
        System.out.println(result);

	}
	
	public static String test() {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(GreetWebService.class);
		factory.setAddress("http://192.168.9.81:9999/store-service/services/aaaa?wsdl");
		GreetWebService client = (GreetWebService) factory.create();
		String result = client.greeting(" David ");
		System.out.println(result);
		
		return result;
	}

}
