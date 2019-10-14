package com.hello.store.test.entity;
import java.util.Date;


/* 
* 用户帐号
* gen by beetlsql 2019-09-25
*/
public class UserAccount   {
	
	private Long id ;
	/*
	禁用状态（0 启用  1 禁用）
	*/
	private Integer disablestate ;
	/*
	是否删除（0未删除1已删除）
	*/
	private Integer isdel ;
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
	private Long updateuser ;
	/*
	创建日期
	*/
	private Date createdate ;
	/*
	修改日期
	*/
	private Date updatedate ;
	
	public UserAccount() {
	}
	
	public Long getId(){
		return  id;
	}
	public void setId(Long id ){
		this.id = id;
	}
	
	/**
	* 禁用状态（0 启用  1 禁用）
	*@return 
	*/
	public Integer getDisablestate(){
		return  disablestate;
	}
	/**
	* 禁用状态（0 启用  1 禁用）
	*@param  disablestate
	*/
	public void setDisablestate(Integer disablestate ){
		this.disablestate = disablestate;
	}
	
	/**
	* 是否删除（0未删除1已删除）
	*@return 
	*/
	public Integer getIsdel(){
		return  isdel;
	}
	/**
	* 是否删除（0未删除1已删除）
	*@param  isdel
	*/
	public void setIsdel(Integer isdel ){
		this.isdel = isdel;
	}
	
	/**
	* 帐号
	*@return 
	*/
	public String getAccount(){
		return  account;
	}
	/**
	* 帐号
	*@param  account
	*/
	public void setAccount(String account ){
		this.account = account;
	}
	
	/**
	* 密码
	*@return 
	*/
	public String getPassword(){
		return  password;
	}
	/**
	* 密码
	*@param  password
	*/
	public void setPassword(String password ){
		this.password = password;
	}
	
	/**
	* 修改人
	*@return 
	*/
	public Long getUpdateuser(){
		return  updateuser;
	}
	/**
	* 修改人
	*@param  updateuser
	*/
	public void setUpdateuser(Long updateuser ){
		this.updateuser = updateuser;
	}
	
	/**
	* 创建日期
	*@return 
	*/
	public Date getCreatedate(){
		return  createdate;
	}
	/**
	* 创建日期
	*@param  createdate
	*/
	public void setCreatedate(Date createdate ){
		this.createdate = createdate;
	}
	
	/**
	* 修改日期
	*@return 
	*/
	public Date getUpdatedate(){
		return  updatedate;
	}
	/**
	* 修改日期
	*@param  updatedate
	*/
	public void setUpdatedate(Date updatedate ){
		this.updatedate = updatedate;
	}
	

}
