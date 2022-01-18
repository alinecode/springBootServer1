package com.hello.store.test.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.ConvertUtils;

/**
 * 让BeanUtils可以copy时间
 * @author AL
 *
 */
public class BeanUtils {

	static {
		// 只处理source中的时间和字符串类型。
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		ConvertUtils.register(new DateConvert(), String.class);
	}

	public static void copyProperties(Object target, Object source) {
		
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
}
