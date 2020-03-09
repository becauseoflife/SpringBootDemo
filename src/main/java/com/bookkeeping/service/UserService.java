package com.bookkeeping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;

public interface UserService {
	
	/*
	 * public void saveUser(UserInfo user) throws Exception;
	 * 
	 * public void updateUser(UserInfo user);
	 * 
	 * public UserInfo queryUserByAccount(String userAccount);
	 */
	
	// 用户注册
	public JSONResult userRedister(UserInfo user);
	
	// 用户登陆
	public JSONResult userLogin(
			@Param("request") HttpServletRequest request, 
			@Param("userAccount")String userAccount, 
			@Param("userPassword")String userPassword);
	
	// 保存记账记录
	public JSONResult saveBookkeeping(
			@Param("request")HttpServletRequest request,
			@Param("date")String date,
			@Param("time")String time,
			@Param("cost")String cost,
			@Param("type")String type);
	
	// 获取首页的数据
	public JSONResult getHomePageData(
			@Param("request")HttpServletRequest request
			);
	
	// 获取历史数据
	public JSONResult getRecords(HttpServletRequest request);
	
	
}
