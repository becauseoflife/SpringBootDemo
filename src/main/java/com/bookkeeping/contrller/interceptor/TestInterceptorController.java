package com.bookkeeping.contrller.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;

@RequestMapping("interceptor")
@RestController
public class TestInterceptorController {

	@RequestMapping("/test1")
	public JSONResult test1() {
		
		
		
		return JSONResult.ok();
	}
	
}
