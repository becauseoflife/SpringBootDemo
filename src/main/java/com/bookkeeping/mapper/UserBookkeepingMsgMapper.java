package com.bookkeeping.mapper;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.pojo.UserInfo;
import com.bookkeeping.utils.MyMapper;

public interface UserBookkeepingMsgMapper extends MyMapper<UserInfo> {
	
	/**
	 * 查询‘表’是否存在
	 * @param tableName
	 * @return
	 */
	public int existTable(@Param("tableName")String tableName);
	
	/**
	 * 删除表
	 * @param tableName
	 * @return
	 */
	public int dropTable(@Param("tableName")String tableName);
	
	/**
	 * 创建表
	 * @param tableName
	 * @return
	 */
	public int createTable(@Param("tableName")String tableName);
	
}