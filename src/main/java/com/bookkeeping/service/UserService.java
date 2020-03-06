package com.bookkeeping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public JSONResult userLogin(HttpServletRequest request, String userAccount, String userPassword);
	
}
