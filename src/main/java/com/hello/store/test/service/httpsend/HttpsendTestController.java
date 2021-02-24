package com.hello.store.test.service.httpsend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("http")
public class HttpsendTestController {

	@Autowired
	HttpsendTest httpsendTest;
	
	@RequestMapping("get")
	public String get() {
		
		String sendtestGet = httpsendTest.sendtestGet();
		
		return sendtestGet;
	}
	
	@RequestMapping("post")
	public String post() {
		
		String sendtestPost = httpsendTest.sendtestPost();
		
		return sendtestPost;
	}
	
	@RequestMapping("file")
	public String file() {
		
		String sendtestfile = httpsendTest.sendtestFile();
		
		return sendtestfile;
	}
	
}
