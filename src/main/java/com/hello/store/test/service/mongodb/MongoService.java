package com.hello.store.test.service.mongodb;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hello.store.test.entity.Test1;

public interface MongoService {

	/**
	 * 查询一段时间内 名称模糊查询
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param pageable
	 * @return
	 */
	public List<Test1> findByNameLikeAndTimeBetween(String name,Date startDate, Date endDate,Pageable pageable) ;

	/**
	 * 批量新增
	 * @param list
	 */
	public void insertList(List<Test1> list);

	public void update(Test1 test1);
	
}
