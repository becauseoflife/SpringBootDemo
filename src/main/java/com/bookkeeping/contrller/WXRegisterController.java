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
	@RequestMapping("/register")
	public JSONResult userRegister(
			String netName,
			String name,
			String telephone,
			String account,
			String password) {
		
		System.out.println(netName + "\n" + name + "\n" + telephone + "\n" + account + "\n" + password + "\n");
		// 检查该用户是否被注册	
		try {
			if (service.queryUserByAccount(account) != null) {
				return JSONResult.errorMsg("账号已经存在");
			}else {
				UserInfo user = new UserInfo();
				user.setName(name);
				user.setNetname(netName);
				user.setTelephone(telephone);
				user.setAccount(account);
				user.setPassword(password);
				
				service.saveUser(user);
				
				return JSONResult.ok("注册成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JSONResult.errorMsg(e.getMessage());
		}
	}
	
}