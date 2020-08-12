package com.hello.store.test.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Test1 {

	@Id
	private String id;
	private String name;
	private int age;
//	@JsonFormat(pattern="yyyy-mm-dd hh:mm",timezone="GMT+8")
	private Date creatdata; // 单词拼写错误，注意和数据库对应

	public Test1() {

	}

	public Test1(String id, String name, int age, Date creatdata) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.creatdata = creatdata;
	}

	public Test1(String name, int age, Date creatdata) {
		super();
		this.name = name;
		this.age = age;
		this.creatdata = creatdata;
	}

	public Date getCreatdata() {
		return creatdata;
	}

	public void setCreatdata(Date creatdata) {
		this.creatdata = creatdata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
