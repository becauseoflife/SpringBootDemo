package com.bookkeeping.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookkeeping.mapper.UserBookkeepingMsgMapper;
import com.bookkeeping.mapper.UserInfoMapper;
import com.bookkeeping.mapper.UserInfoMapperCustom;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userMapper;
	
	@Autowired
	private UserBookkeepingMsgMapper userOperMsgMapper;
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private UserInfoMapperCustom customMapper;

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
			if (customMapper.queryUserByAccount(user.getAccount()) != null) {
				return JSONResult.errorMsg("账号已经存在");
			}else {
				// 创建id
				String id = sid.nextShort();
				user.setId(id);
				// 创建对应表储存信息
				String tableName = id;
				userOperMsgMapper.createTable(tableName);
				
				if(userOperMsgMapper.existTable(tableName) > 0) {
					System.out.println("创建成功");
				}else {
					System.out.println("创建失败");
				}
				
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
	public JSONResult userLogin(HttpServletResponse response, String userAccount, String userPassword) {
		// TODO Auto-generated method stub
		// 检车账号是否存在
		UserInfo user = new UserInfo();
		user = customMapper.queryUserByAccount(userAccount);
		if(user == null) {
			return JSONResult.errorMsg("账号不存在，请先注册");
		}else {
			// 检查密码是否正确
			//System.out.println("user password: " + user.getPassword());
			if(!user.getPassword().equals(userPassword)) {
				return JSONResult.errorMsg("密码错误");
			}else {
				// 登录成功返回sessionId	
				
				
				
				return JSONResult.ok("登录成功");
			}
		}

	}

}
