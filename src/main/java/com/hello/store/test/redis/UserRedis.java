package com.hello.store.test.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.hello.store.test.dao.UserAccountDao;
import com.hello.store.test.entity.UserAccount;

/**
 * 
 * 相关api https://docs.spring.io/spring-boot/docs/2.1.0.M3/reference/html/boot-features-caching.html 注意版本
 * 
 * 假设有大量用户，要将用户放到高可用的Redis里
 * 
 * @author 
 *
 */
//@Configuration // 启动时加载配置      使用时，此处注解不要注释掉了
//@EnableCaching // 开启缓存
public class UserRedis {

	@Autowired
	UserAccountDao accountDao;
	
	private static final String USERINFO_KEY="userInfo:user";
	
	
	@Autowired
	StringRedisTemplate stringRedisTemplate1;
	
	private static StringRedisTemplate stringRedisTemplate;
	
	
	@Bean
	public UserRedis init() {
		
		stringRedisTemplate =stringRedisTemplate1;
		
			List<UserAccount> list = accountDao.all();
			for (UserAccount user : list) {
				if (stringRedisTemplate.opsForHash().hasKey(USERINFO_KEY, user.getAccount())) {
					stringRedisTemplate.opsForHash().delete(USERINFO_KEY, user.getAccount());
					stringRedisTemplate.opsForHash().put(USERINFO_KEY, user.getAccount(), JSON.toJSONString(user));
				} else {
					stringRedisTemplate.opsForHash().put(USERINFO_KEY, user.getAccount(), JSON.toJSONString(user));
				}
			}
		return this;
	}
	
	/**
	 * 获得值
	 * 
	 * @param userName
	 * @return
	 */
	public static UserAccountDao getUser(String userName) {
		if (stringRedisTemplate.opsForHash().hasKey(USERINFO_KEY, userName)) {
			
//			return  JSON.toJSON(javaObject, parserConfig)((String)stringRedisTemplate.opsForHash().get(USERINFO_KEY, userName), UserAccountDao.class);
			
//			JSON.toJSON(javaObject)
			
//			JSON toJSON = JSON.parseObject("");
			
			return JSON.toJavaObject(JSON.parseObject(stringRedisTemplate.opsForHash().get(USERINFO_KEY, userName).toString()),UserAccountDao.class);
			
		} else {
			return null;
		}
	}

	public static void deleteUser(String userName) {
		if (stringRedisTemplate.opsForHash().hasKey(USERINFO_KEY, userName)) {
			stringRedisTemplate.opsForHash().delete(USERINFO_KEY, userName);
		}
	}

	/**
	 * 设置值
	 * 
	 * @param userName
	 * @param user
	 */
	public static void setUser(String userName, UserAccountDao user) {

		stringRedisTemplate.opsForHash().put(USERINFO_KEY, userName, JSON.toJSONString(user));

	}
	
}
