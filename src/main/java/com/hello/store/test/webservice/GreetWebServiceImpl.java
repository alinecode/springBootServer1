package com.hello.store.test.webservice;

import javax.jws.WebService;

import org.springframework.stereotype.Service;


/**
 *  参考 https://cxf.apache.org/docs/springboot.html  
 *	普通释义： https://www.jianshu.com/p/49d7997ad3b7
 * 	项目参看： https://www.cnblogs.com/wlv1314/p/12157568.html
 *  流程参考： https://spring.io/guides/gs/producing-web-service/
 *  非springboot的包引用可看这个（不是jetty可以不要jetty这个包）：  https://cxf.apache.org/docs/using-cxf-with-maven.html
 *  
 * @author 
 * 
 */
@WebService
@Service
public class GreetWebServiceImpl implements GreetWebService {
    @Override
    public String greeting(String hello) {
        return "Good morning : "+hello;
    }
}