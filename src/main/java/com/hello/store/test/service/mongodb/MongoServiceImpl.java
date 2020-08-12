package com.hello.store.test.service.mongodb;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hello.store.test.entity.Test1;

@Service
public class MongoServiceImpl implements MongoService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Test1> findByNameLikeAndTimeBetween(String name, Date startDate, Date endDate, Pageable pageable) {
		
		// 使用 spring data方式查询
		Query query = new Query();

		// 模糊查询需要自己写regex 此处为包含名称。并且Enables case-insensitive matching. 
		Pattern pattern = Pattern.compile(name,Pattern.CASE_INSENSITIVE);
		// 简单的字符：//模糊查询以 ^开始 以$结束 .*相当于sql中的%  比如 "^1.*$" 查询以1开头的
		Criteria lte = Criteria.where("creatdata").gt(startDate).lte(endDate).and("name").regex(pattern);
		
		query.addCriteria(lte).with(pageable);
//		query.addCriteria(lte);

		List<Test1> find = mongoTemplate.find(query, Test1.class);

		return find;
		
		
		// 也可以使用com.mongodb包下的BasicDBObject
//		BasicDBObject query2 = new BasicDBObject("fieldName",
//				   new BasicDBObject("$gte",startDate).append("$lt",endDate ));
//		// 或者不在构造函数中使用new
//		query2.put("dateAdded", BasicDBObjectBuilder.start("$gte", startDate).add("$lte", endDate).get());
		
	}

	@Override
	public void insertList(List<Test1> list) {
		
		// 批量新增
		mongoTemplate.insert(list, "test1");
		// 新增一个
//		mongoTemplate.insert(list.get(0), "test1");
		
	}

	@Override
	public void update(Test1 test1) {
		// TODO Auto-generated method stub
		
	}

}
