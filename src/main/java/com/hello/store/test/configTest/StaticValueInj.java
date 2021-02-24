package com.hello.store.test.configTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Value 注解的使用
 * 使用注解注入变量、注入静态变量
 * 
 * @author 
 *
 */
@Component
public class StaticValueInj {

	static String t11;
	
	/**
	 * '@Value作用方法时 优先级比 '@Autowired作用方法时 低，@Value作用变量时 优先级比@Autowired作用方法时优先级高
	 * @param t1
	 */
	@Value("${xxx.t1}")
	public void setT1(String t1) {
		t11 = t1;
	}
	
	@Value("${xxx.t2}")
	String t2;
	
	@Autowired // 启动时运行
	public void autoPrintTest() {
		
		System.err.println("t1 ===> "+t11); // 在这两个注解都作用在方法上时，@Value优先级要比@Autowired低，所以输出null，
		// 也就是会先执行@Autowired修饰方法，再执行@Value修饰方法。也就是不在@Autowired中运行时，setT1可以正常注入
		
		System.err.println("t2 ===> "+t2); // 作用在变量中时，可以正常输出，优先级较高
		
	}
	
	public void printtest() {
		
		System.err.println("t1 ===> "+t11); // 其他地方调用时，此处可以正常输出注入的值
		
		System.err.println("t2 ===> "+t2);
		
	}
	
}
