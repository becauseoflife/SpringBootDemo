package com.bookkeeping.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.service.UserService;

@RestController
class WXLoginController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public JSONResult wxLogin(String account, String password) {
		
		System.out.println("account:" + account + "  " + "password:" + password);
		// 检车账号是否存在
		UserInfo user = new UserInfo();
		user = service.queryUserByAccount(account);
		if(user == null) {
			return JSONResult.errorMsg("账号不存在，请先注册");
		}else {
			// 检查密码是否正确
			System.out.println("user password: " + user.getPassword());
			if(!user.getPassword().equals(password)) {
				return JSONResult.errorMsg("密码错误");
			}else {
				return JSONResult.ok("登录成功");
			}
		}

	}
}
