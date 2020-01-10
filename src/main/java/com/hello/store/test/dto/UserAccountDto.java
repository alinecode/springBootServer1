package com.hello.store.test.dto;

import io.swagger.annotations.ApiModelProperty;

/* 
* 用户帐号
* gen by beetlsql 2019-09-25
*/
public class UserAccountDto   {
	
	@ApiModelProperty(value = "用户id")
	private String id ;
	/*
	禁用状态（0 启用  1 禁用）
	*/
	private String disablestate ;
	/*
	是否删除（0未删除1已删除）
	*/
	private String isdel ;
	/*
	帐号
	*/
	private String account ;
	/*
	密码
	*/
	private String password ;
	/*
	修改人
	*/
	private String updateuser ;
	/*
	创建日期
	*/
	private String createdate ;
	/*
	修改日期
	*/
	private String updatedate ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisablestate() {
		return disablestate;
	}
	public void setDisablestate(String disablestate) {
		this.disablestate = disablestate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	
	

}
