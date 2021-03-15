package com.hello.store.test.service.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 多线程注解的使用。
 * 
 * 在主类上使用@EnableAsync注解，
 * 然后再在spring管理的类中的方法上使用@Async注解，该方法就是多线程的。
 * 
 * @author AL
 *
 */
@Component
public class AsyncServiceImpl {

	/**
	 * @param aa
	 * @return Future 返回这个才能获取到返回值，不能直接返回。一般异步方法是不需要返回值的( void )
	 * Future的get方法会影响效率
	 */
	@Async
	public Future<String> run(String aa) {
		
		long currentTimeMillis = System.currentTimeMillis();
		
		System.err.println("---------异步线程---------" + Thread.currentThread().getName());
		
		return new AsyncResult<String>(String.valueOf(currentTimeMillis)) ;
	}
}
