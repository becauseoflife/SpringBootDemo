package com.bookkeeping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookkeeping.mapper.UserInfoMapper;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userMapper;

	/*
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) // 事务的回滚 public void
	 * saveUser(UserInfo user) throws Exception { // TODO Auto-generated method stub
	 * userMapper.insert(user); }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * updateUser(UserInfo user) { // TODO Auto-generated method stub
	 * userMapper.updateByPrimaryKey(user); }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.SUPPORTS) public UserInfo
	 * queryUserByAccount(String userAccount) { // TODO Auto-generated method stub
	 * return userMapper.selectByPrimaryKey(userAccount); }
	 */

	// 用户注册
	@Override
	@Transactional(propagation = Propagation.REQUIRED)		// 事务的回滚
	public JSONResult userRedister(UserInfo user) {
		// 检查用户是否存在
		try {
			if (userMapper.selectByPrimaryKey(user.getAccount()) != null) {
				return JSONResult.errorMsg("账号已经存在");
			}else {
				userMapper.insert(user);
				return JSONResult.ok("注册成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JSONResult.errorMsg(e.getMessage());
		}
	}

	// 用户登陆
	@Override
	public JSONResult userLogin(String userAccount, String userPassword) {
		// TODO Auto-generated method stub
		// 检车账号是否存在
		UserInfo user = new UserInfo();
		user = userMapper.selectByPrimaryKey(userAccount);
		if(user == null) {
			return JSONResult.errorMsg("账号不存在，请先注册");
		}else {
			// 检查密码是否正确
			//System.out.println("user password: " + user.getPassword());
			if(!user.getPassword().equals(userPassword)) {
				return JSONResult.errorMsg("密码错误");
			}else {
				return JSONResult.ok("登录成功");
			}
		}

	}

}
