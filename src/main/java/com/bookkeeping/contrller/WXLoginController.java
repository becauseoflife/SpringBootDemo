package com.bookkeeping.contrller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.service.UserService;

@RestController
public class WXLoginController {
	
	@Autowired
	private UserService loginOperService;
	
	@RequestMapping("/userLogin")
	public JSONResult login(HttpServletRequest request , String account, String password) {
		
		//System.out.println("account:" + account + "  " + "password:" + password);
		
		return loginOperService.userLogin(request, account, password);
	}
	
}
