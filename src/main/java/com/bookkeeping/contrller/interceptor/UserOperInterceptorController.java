package com.bookkeeping.contrller.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;

@RequestMapping("userOperation")
@RestController
public class UserOperInterceptorController {

	@RequestMapping("/test")
	public JSONResult test(String account) {
		
		return JSONResult.ok();
	}
	
	
	
	
}
