package com.hello.store.test.configTest;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aspect 加强处理。（可用于记录方法运行时间、记录日志等） 
 * 参考： 官方文档：
 * https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/aop.html
 * 易懂文档： 
 * https://www.cnblogs.com/bigben0123/p/7779357.html
 * https://howtodoinjava.com/spring-boot2/aop-aspectj/
 * https://www.baeldung.com/spring-aop-annotation
 * https://www.baeldung.com/spring-aop-pointcut-tutorial
 * 
 * 官方文档还描述了动态代理机制的选择：  只要目标对象实现了接口，那么就使用java的jdk代理。（因为JDK代理只提供基于接口的代理）
 * 否则就要使用spring包包含的CGLIB代理
 * 
 * 官方文档10.6.1介绍了spring AOP是基于代理的（proxy-based），10.7介绍了@AspectJ代理
 * 
 * 
 * 
 * @author AL
 *
 */
@Aspect
@Component // 这俩注解使AOP功能生效，在官方文档的10.2.2
public class AspectConfig {

	/**
	 * 这个方法用于捕获要执行增强的方法，比如我们此处要
	 * 声明要对哪些方法增强 第一个*表示任意返回值，最后括号里的加两个点表示不管有多少个参数。 pointcut
	 * 里面的execution等表达式可以看官方文档的10.2.3
	 * 
	 * 
	 */
	@Pointcut("execution(* com.hello..*Controller.*(..))")
	private void webLog() {

	}

	/**
	 * 运行在 被增强的方法执行前 
	 * @param joinPoint
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {

//		从JoinPoint中可以获取方法名和所有的入参
		
//		System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
//				+ joinPoint.getSignature().getName());
//		System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		
//		获取第一个String类型的参数
//		String aaString = (String) joinPoint.getArgs()[0];
		
//		System.err.println(aaString);
		
	}

    //后置异常通知  
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){
        System.out.println("需被增强的方法异常时执行.....");  
    }
	

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
     * 
     *   原文：
     * After (finally) advice runs however a matched method execution exits.
     * It is declared using the @After annotation. 
     * After advice must be prepared to handle both normal and exception return conditions.
     * It is typically used for releasing resources, etc.
     * 
     * @param jp
     */
    @After("webLog()")
    public void after(JoinPoint jp){  
//        System.out.println("需被增强的方法最后执行.....");  
    }
    
    /**
     * 方法执行返回后执行。可以获取返回值，可以通过反射获取每一个属性的值
     * ，但是经过测试，此处的反射会非常消耗性能，影响响应时间。
     * 如果提前知道是固定的返回类型，那么把Object换成具体的类型即可，就不需要反射来获取内容了
     * 
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
//        System.out.println("方法的返回值 : " + ret);
        
		// 通过反射 获取所有变量
//		Field[] fields = ret.getClass().getDeclaredFields();  // 获取实体类的所有属性，返回Field数组
//		StringBuilder builder = new StringBuilder();
//		for (int i = 0; i < fields.length; i++) {
//			fields[i].setAccessible(true);
//			builder.append(fields[i].getName( ) + "=" + fields[i].get( ret ) +",\n");
//		}
//		
//		System.err.println(builder.toString());
        
    }  
	
	/**
	 * 环绕增强。注解的参数是被@Pointcut注解的方法。这个参数也可以是和@Pointcut的参数一样的(比如execution这些)
	 * 
	 * 根据官方文档，需要线程安全时，比如要启动或停止一个定时器的时候建议使用环绕增强， 否则建议使用the least
	 * powerful form of advice，比如before advice
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
//	@Around("webLog()")
//	public Object arround(ProceedingJoinPoint pjp) throws Throwable {
//
//		long currentTimeMillis = System.currentTimeMillis();
//		Object proceed = pjp.proceed();
//		long currentTimeMillis2 = System.currentTimeMillis();
//
//		System.err.println("方法执行的时间：" + (currentTimeMillis2 - currentTimeMillis)+" ms");
//
//		return proceed;
//
//	}

}
