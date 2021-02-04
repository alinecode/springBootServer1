package com.hello.store.test.service.mongodb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hello.store.test.entity.Test1;
import com.hello.store.test.mongodb.Test1Repository;

/**
 * MongoDB，测试中20210128
 * @author 
 *
 */
@RestController
@RequestMapping("/mon")
public class MonController {

	@Autowired
	Test1Repository test1Repository;
	@Autowired
	MongoService mongoService;

	/**
	 * 测试查询
	 * @return
	 */
	@RequestMapping(value = "/test4")
	public String test4() {

		try {

			Sort sort = Sort.by(Sort.Direction.ASC, "name");
			Pageable pageable = PageRequest.of(0, 2, sort);  // 当前页下标从0开始
			String sdate = "2020-05-31 06-06-06";
			String sdate2 = "2020-06-01 23-06-06";
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date date1 = simpleDateFormat1.parse(sdate);
			Date date2 = simpleDateFormat1.parse(sdate2);

			List<Test1> list = mongoService.findByNameLikeAndTimeBetween("小", date1, date2, pageable);

			String reString = JSON.toJSONString(list);
			return reString;

		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}

	}
	
	/**
	 * 测试新增
	 * @return
	 */
	@RequestMapping(value = "/test5")
	public String test5() {
		
		try {
			

//			List<Test1> list = new ArrayList<>();
//			
////			list.add(new Test1("小0", 10, new Date()));
//			list.add(new Test1("小1", 11, new Date()));
//			list.add(new Test1("小2", 12, new Date()));
//			list.add(new Test1("小3", 13, new Date()));
//			list.add(new Test1("小4", 14, new Date()));
//			
//			mongoService.insertList(list);
			
			return "1";
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		
	}
	
	
	
	@RequestMapping("testdel")
	public String testdel(String id) {

		if (StringUtils.isBlank(id)) {
			id = "5ed88e7c045923244caf7320";
		}
		
		Test1 test1 = new Test1();
		
		test1.setId(id);
		
		mongoService.delete(test1);
		
		return "1";
	
		
		
	}
	
	
	@RequestMapping(value = "/testupdate")
	public String testupdate() {
		
		try {
			
			
//			List<Test1> list = new ArrayList<>();
//			
////			list.add(new Test1("小0", 10, new Date()));
//			list.add(new Test1("小1", 11, new Date()));
//			list.add(new Test1("小2", 12, new Date()));
//			list.add(new Test1("小3", 13, new Date()));
//			list.add(new Test1("小4", 14, new Date()));
//			
//			mongoService.insertList(list);
			
			Test1 test1 = new Test1("小红红", 14, new Date());
			
			mongoService.update(test1);;
			
			return "1";
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		
	}
	
	/**
	 * 测试修改
	 * @return
	 */
	@RequestMapping(value = "/test6")
	public String test6() {
		
		try {
			
			Test1 test1 = new Test1("5ed88e7c045923244caf7320", "小00", 10, new Date());
			
			mongoService.update(test1);
			
			return "1";
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		
	}

	
	
	
//	@RequestMapping(value = "/test1")
//	public String test1(HttpServletRequest request) {
//
//		try {
//
//			List<Test1> findByNameLike = test1Repository.findByNameLike("g");
//			
//			String reString = JSON.toJSONString(findByNameLike);
//
//			return reString;
//		} catch (Exception e) {
//			return e.getLocalizedMessage();
//			// TODO: handle exception
//		}
//
//	}

	@RequestMapping(value = "/test2")
	public String test1(HttpServletRequest request) {

		try {

			Sort sort = Sort.by(Sort.Direction.ASC, "age");

			Pageable pageable = PageRequest.of(1, 1, sort);

			Page<Test1> findByNameLike = test1Repository.findByNameLike("小", pageable);

			String reString = JSON.toJSONString(findByNameLike);

			return reString;
		} catch (Exception e) {
			return e.getLocalizedMessage();
			// TODO: handle exception
		}

	}

	@RequestMapping(value = "/test3")
	public String test3(HttpServletRequest request) {

		try {

//			Sort sort = Sort.by(Sort.Direction.ASC, "age");
//			
//			Pageable pageable = PageRequest.of(0,1,sort);

//			String sdate = "2020-06-01 06-06-06";
//			String sdate2 = "2020-06-01 23-06-06";
//			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//			SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

//			......

//			Page<Test1> findByNameLike = test1Repository.findByNameLike("小",sdate5 ,sdate6, pageable);

//			String reString = JSON.toJSONString(findByNameLike);

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
			// TODO: handle exception
		}

	}

}
