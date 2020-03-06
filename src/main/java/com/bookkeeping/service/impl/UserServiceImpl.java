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
import com.bookkeeping.model.WXSessionModel;
import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;
import com.bookkeeping.utils.JsonUtils;
import com.bookkeeping.utils.RedisOperator;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userMapper;		// 数据库对表user_info的操作
	
	@Autowired
	private UserBookkeepingMsgMapper userOperMsgMapper;		
	
	@Autowired
	private Sid sid;		// id生成器
	
	@Autowired
	private UserInfoMapperCustom customMapper;	// 通过某个字段查询用户信息（UserInfo.java）
	
	@Autowired
	private RedisOperator redisOper;		// 对Redis的操作

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
	public JSONResult userLogin(HttpServletRequest request, String userAccount, String userPassword) {
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
				// 登录成功 保存session并返回sessionId	
				System.out.println("SeesionId:" + request.getSession().getId());
				String sessionId = request.getSession().getId();
				
				WXSessionModel model = new WXSessionModel();
				model.setSessionId(sessionId);
				model.setExpiredTime(1 * 24 * 60 * 60 * 1000);
				model.setUserId(user.getId());
				model.setUserAccount(user.getAccount());
				model.setUserPassword(user.getPassword());
				
				// 保存session到redis
				redisOper.set("user-redis-session:" + model.getUserAccount(), JsonUtils.objectToJson(model), model.getExpiredTime());
				
				return JSONResult.ok("登录成功", model);
			}
		}

	}

}
