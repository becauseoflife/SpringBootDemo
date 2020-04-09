package com.bookkeeping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.pojo.JSONResult;
import com.bookkeeping.pojo.UserInfo;

public interface UserService {
	
	/*
	 * public void saveUser(UserInfo user) throws Exception;
	 * 
	 * public void updateUser(UserInfo user);
	 * 
	 * public UserInfo queryUserByAccount(String userAccount);
	 */
	
	// 用户注册
	public JSONResult userRedister(UserInfo user);
	
	// 用户登陆
	public JSONResult userLogin(
			@Param("request") HttpServletRequest request, 
			@Param("userAccount")String userAccount, 
			@Param("userPassword")String userPassword);
	
	// 保存记账记录
	public JSONResult saveBookkeeping(
			@Param("request")HttpServletRequest request,
			@Param("date")String date,
			@Param("time")String time,
			@Param("cost")String cost,
			@Param("type")String type,
			@Param("imgPath")String imgPath
			);
	
	// 获取首页的数据
	public JSONResult getHomePageData(
			@Param("request")HttpServletRequest request
			);
	
	// 获取历史数据
	public JSONResult getRecords(@Param("request")HttpServletRequest request, @Param("type")String type);
	
	// 获取饼状图的数据
	public JSONResult getPieChartData(
			@Param("request")HttpServletRequest request, 
			@Param("typeArray")String[] typeArray
			);
	
	// 保存心愿存钱
	public JSONResult saveWishMoney(
			@Param("request")HttpServletRequest request, 
			@Param("wishMoney")String wishMoney
			);
	
	// 获取心愿存钱数
	public JSONResult getWishMoney(
		@Param("request")HttpServletRequest request
	);
	
	
	// 修改密码
	public JSONResult changePwd(
			@Param("request")HttpServletRequest request, 
			@Param("oldPwd")String oldPwd,
			@Param("newPwd")String newPwd
			);
	
	// 保存本周和本月最大花费额度
	public JSONResult saveLimitCost(
			@Param("request")HttpServletRequest request, 
			@Param("weekMaxCost")String weekMaxCost,
			@Param("monthMaxCost")String monthMaxCost
			);
	
	// 获取本周和本月最大消费额度
	public JSONResult getLimitCost(@Param("request")HttpServletRequest request);
	
	// 获取本周和本月的花费
	public JSONResult getWeekMonthCost(@Param("request")HttpServletRequest request);
	
	// 修改个人信息（网名和手机号）
	public JSONResult resetUserInfo(
			@Param("request")HttpServletRequest request,
			@Param("netName")String netName,
			@Param("telephone")String telephone
			);
	
}
