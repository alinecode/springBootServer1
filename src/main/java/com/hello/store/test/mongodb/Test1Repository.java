package com.hello.store.test.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hello.store.test.entity.Test1;

/**
 * https://spring.io/guides/gs/accessing-data-mongodb/
 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference
 *
 */
public interface Test1Repository extends MongoRepository<Test1, Long> {
	
    /** 
     * 根据名称模糊查询 
     * @param name 
     * @return 
     */
    List<Test1> findByNameLike(String name);
}