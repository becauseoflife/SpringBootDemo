package com.bookkeeping.contrller.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.service.UserService;

@RequestMapping("userOperation")
@RestController
public class UserOperInterceptorController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/saveBookkeeping")
	public JSONResult saveBookkeeping(HttpServletRequest request, String date, String time, String cost, String type) {
		
		//System.out.println("date:" + date + " time:" + time + " cost:" + cost + " type:" + type);
		
		return userService.saveBookkeeping(request, date, time, cost, type);
	}
	
	@RequestMapping("/getHomePageData")
	public JSONResult getHomePageData(HttpServletRequest request) {
		
		return userService.getHomePageData(request);
	}
	
	@RequestMapping("/getRecord")
	public JSONResult getRecord(HttpServletRequest request) {
		
		return userService.getRecords(request);
	}
	
}
