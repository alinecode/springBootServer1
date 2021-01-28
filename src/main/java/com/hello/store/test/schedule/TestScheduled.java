package com.hello.store.test.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 
 * 定时器定时执行。启动类加上开启注解。
 *  参考：https://spring.io/guides/gs/scheduling-tasks/
 *  
 * cron表达式参考：
 * https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
 * https://www.freeformatter.com/cron-expression-generator-quartz.html
 * 
 * @author 
 *
 */
//@Component  // 让注解方法@Scheduled和@Bean生效。
public class TestScheduled {

//	@Scheduled(cron = "0 0 12 * * ?") // 每天中午12点执行。 Fire at 12:00 PM (noon) every day
	@Scheduled(fixedDelay = 10000) // 10秒钟执行一次
	public void setname() {
		
		// 执行一些方法。比如定时发送请求、定时删除日志文件、定时删除垃圾文件等。
		System.err.println("aaa");
		
	}
	
	/**
	 * 解决 No TaskScheduler/ScheduledExecutorService bean found for scheduled processing。
	 * 不要这个方法也可以正常执行。
	 * 参考：https://stackoverflow.com/questions/30431776/using-scheduled-and-enablescheduling-but-gives-nosuchbeandefinitionexception
	 * @return
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}
	
	
//	/**
//	 * 这个也可以
//	 * @return
//	 */
//	@Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(8);
//        scheduler.setThreadNamePrefix("scheduled-thread-");
//        return scheduler;
//    }
	
}
