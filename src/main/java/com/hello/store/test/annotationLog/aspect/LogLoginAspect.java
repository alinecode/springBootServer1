package com.hello.store.test.annotationLog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 自定义注解的处理
 * 
 * @author Al
 *
 */
@Aspect
@Component
public class LogLoginAspect {

    @Pointcut("@annotation(com.hello.store.test.annotationLog.annotation.LogLogin)")
    public void logPointCut() {

    }
	
    @Around("logPointCut()")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        
        System.out.println(time);
        
        //保存日志
//        saveLoginLog(point, time);

        return result;
    }
    
    
}
