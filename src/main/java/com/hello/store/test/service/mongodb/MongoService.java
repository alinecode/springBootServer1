package com.hello.store.test.service.mongodb;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hello.store.test.entity.Test1;

public interface MongoService {

	public List<Test1> findByNameLikeAndTimeBetween(String name,Date startDate, Date endDate,Pageable pageable) ;
	
}
