package com.hello.store.test.service.httpsend;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
//@Order(99999)
public class HttpsendTest {

	@Autowired
	RestTemplate restTemplate;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Autowired
//	public void test() {
//		sendtestGet();
//	}
	
	/**
	 * get方法
	 * @return
	 */
	public String sendtestGet() {
		
		// 带参数的url，把变量用大括号括起来
		String url = "http://127.0.0.1:9995/store-service/user/test?aa={aa}";
		
		// 设置请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		
		// 设置参数。和上方的url里面的变量一一对应
		Map<String, String> params = new HashMap<>();
	    params.put("aa", "aa-string");
		
	    // 执行get请求，有多种方法
	    // 方法1 常用方法
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, params); 
		// ↑ 可以携带请求头。看返回的什么数据格式，可以将String.class替换成对应的class，会自动替换进去
		// 方法2 不需要看header等数据时的使用方法
//		String forObject = restTemplate.getForObject(url, String.class, params); // 参数无法携带请求头,返回结果无法查看header等数据
		// 方法3
//		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class, params); // 类似方法1
		System.err.println("----->");
		System.err.println("get");
		System.err.println(exchange.getBody());
		
		return exchange.getBody();

	}
	
	/**
	 * post方法
	 * @return
	 */
	public String sendtestPost() {
		
		// 
		String url = "http://127.0.0.1:9995/store-service/user/test";
		
		// 可以设置请求头
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON); // 和接收方的@RequestBody对应
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// 设置参数。和上方的url里面的变量一一对应
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
	    params.add("aa", "aa-string");
		
	    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(params,headers);
	    // post和get类似，区别是传参方式等，需要使用MultiValueMap
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class); 
	    // 方式2 ... 方式3 ...
	    
		System.err.println("----->");
		System.err.println("post");
		System.err.println(exchange.getBody());
	    
	    return exchange.getBody();
	    
	}
	
	/**
	 * 发送文件
	 * @return 
	 */
	public String sendtestFile() {
		
		// 设置url
		String url = "http://127.0.0.1:9995/store-service/user/file";
		
		// 设置请求头
//		HttpHeaders headers = new HttpHeaders();
		
		// 设置请求体
		FileSystemResource resource = new FileSystemResource(new File("D:/TEMP/upload/aa.txt"));
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
	    params.add("aa", resource); // 注意这里的aa，和接收端的 @RequestParam("aa") MultipartFile multipartFile 一致，否则不是变量名multipartFile则无法接收
		
	    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(params); 

	    // 执行发送
	    ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class); 
	    
	    return exchange.getBody();
	}
	
}
