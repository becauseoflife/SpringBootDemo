package com.bookkeeping.mapper;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.pojo.UserInfo;

public interface UserInfoMapperCustom {

	// 通过用户id查找用户
	public UserInfo queryUserById(@Param("id")String id);
	
	// 通过用户账号查找用户
	public UserInfo queryUserByAccount(@Param("account")String account);
	
}
