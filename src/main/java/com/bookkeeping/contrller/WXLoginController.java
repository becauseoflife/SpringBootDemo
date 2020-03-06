package com.bookkeeping.contrller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public JSONResult wxLogin(HttpServletResponse response, String account, String password) {
		
		System.out.println("account:" + account + "  " + "password:" + password);
		
		
		return service.userLogin(response, account, password);
	}
}
