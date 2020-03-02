package com.bookkeeping.service;

import com.bookkeeping.pojo.UserInfo;

public interface UserService {
	
	public void saveUser(UserInfo user) throws Exception;
	
	public void updateUser(UserInfo user);
	
	public UserInfo queryUserByAccount(String userAccount);
	
	// 用户注册
	public void userRedister(UserInfo user);
}
