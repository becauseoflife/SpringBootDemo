package com.bookkeeping.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;


@RestController
public class WXRegisterController {

	@Autowired 
	private UserService service;
	
	/**
	 * 用户注册接口
	 * @param netName	昵称
	 * @param name		真实姓名
	 * @param telephone 电话号码
	 * @param account	账号
	 * @param password	密码
	 * @return 注册结果
	 */
	@RequestMapping("/userRegister")
	public JSONResult userRegister(
			String netName,
			String name,
			String telephone,
			String account,
			String password) {
		
		//System.out.println(netName + "\n" + name + "\n" + telephone + "\n" + account + "\n" + password + "\n");

		UserInfo user = new UserInfo();
		user.setName(name);
		user.setNetname(netName);
		user.setTelephone(telephone);
		user.setAccount(account);
		user.setPassword(password);
		user.setHopeSave(-1.0);
		user.setWeekMaxCost(-1.0);
		user.setMonthMaxCost(-1.0);
		
		return service.userRedister(user);
	}
	
}
