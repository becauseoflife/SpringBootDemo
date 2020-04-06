package com.bookkeeping.contrller.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.service.UserService;

@RequestMapping("userOperation")
@RestController
public class UserOperInterceptorController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/saveBookkeeping")
	public JSONResult saveBookkeeping(HttpServletRequest request, String date, String time, String cost, String type, String imgPath) {
		
		System.out.println("date:" + date + " time:" + time + " cost:" + cost + " type:" + type + " symbolOfCost: ");
		
		return userService.saveBookkeeping(request, date, time, cost, type, imgPath);
	}
	
	@RequestMapping("/getHomePageData")
	public JSONResult getHomePageData(HttpServletRequest request) {
		
		return userService.getHomePageData(request);
	}
	
	@RequestMapping("/getRecord")
	public JSONResult getRecord(HttpServletRequest request) {
		
		return userService.getRecords(request);
	}
	
	@RequestMapping("/getPieChartsData")
	public JSONResult getPieChartsData(HttpServletRequest request, String[] typeArray) {
	
		/*
		 * for (String type:typeArray) { System.out.println(type); }
		 */

		return userService.getPieChartData(request, typeArray);
	}
	
	@RequestMapping("/saveWishMoney")
	public JSONResult saveWhisMoney(HttpServletRequest request, String wishMoney) {
		
		//System.out.println(wishMoney);
		
		return userService.saveWishMoney(request, wishMoney);
	}
	
	@RequestMapping("/getWishMoney")
	public JSONResult getWishMoney(HttpServletRequest request) {
		
		return userService.getWishMoney(request);
	}
	
	@RequestMapping("/changePassword")
	public JSONResult changePwd(HttpServletRequest request, String oldPwd, String newPwd) {
		
		//System.out.println(oldPwd + "  " + newPwd);
		
		return userService.changePwd(request, oldPwd, newPwd);
	}
	
	@RequestMapping("/saveLimitCost")
	public JSONResult saveLimitCost(HttpServletRequest request, String weekMaxCost, String monthMaxCost) {
		
		System.out.println("本周最大花费：" + weekMaxCost + " 本月最大花费：" + monthMaxCost);
		
		return userService.saveLimitCost(request, weekMaxCost, monthMaxCost);
	}
	
	@RequestMapping("/getLimitCost")
	public JSONResult getLimitCost(HttpServletRequest request) {
		
		return userService.getLimitCost(request);
	}
	
	@RequestMapping("/getWeekMonthCost")
	public JSONResult getWeekMonthCost(HttpServletRequest request) {
		
		return userService.getWeekMonthCost(request);
	}
	
	@RequestMapping("/resetUserInfo")
	public JSONResult resetUserInfo(HttpServletRequest request, String netName, String telephone) {
		
		return userService.resetUserInfo(request, netName, telephone);
	}
	
}
