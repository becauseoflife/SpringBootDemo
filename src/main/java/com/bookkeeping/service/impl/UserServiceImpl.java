package com.bookkeeping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookkeeping.mapper.UserInfoMapper;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)		// 事务的回滚
	public void saveUser(UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(UserInfo user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserInfo queryUserByAccount(String userAccount) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userAccount);
	}

	// 用户注册
	@Override
	public void userRedister(UserInfo user) {
		
	}

}
