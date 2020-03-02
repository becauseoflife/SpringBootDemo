package com.bookkeeping.contrller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;

@RestController
class WXLoginController {
	
	@PostMapping("/wxLogin")
	public JSONResult wxLogin(String code) {
		
		System.out.print("wxLogin code :" + code);
		
		return JSONResult.ok();
	}
}
