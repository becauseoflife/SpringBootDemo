package com.bookkeeping.mapper;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.appdata.entity.LimitCostData;
import com.bookkeeping.pojo.UserInfo;

public interface UserInfoMapperCustom {

	// 通过用户id查找用户
	public UserInfo queryUserById(@Param("id")String id);
	
	// 通过用户账号查找用户
	public UserInfo queryUserByAccount(@Param("account")String account);
	
	// 保存用户的心愿存钱
	public void updateByUserId(@Param("userId")String userId, @Param("wishMoney")String wishMoney);
	
	// 修改密码
	public void updatePassword(@Param("userId")String userId, @Param("newPwd")String newPwd);
	
	// 保存周最大消费额度
	public void updateWeekMaxCost(@Param("userId")String userId, @Param("weekMaxCost")String weekMaxCost);
	
	// 保存月最大消费额度
	public void updateMonthMaxCost(@Param("userId")String userId, @Param("monthMaxCost")String monthMaxCost);
	
	// 获取本周和本月的最大消费的额度
	public LimitCostData queryMaxCost(@Param("userId")String userId);
}
