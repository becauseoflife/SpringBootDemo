package com.bookkeeping.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.bookkeeping.pojo.UserRecord;
import com.bookkeeping.utils.MyMapper;

public interface UserRecordMapper extends MyMapper<UserRecord> {
	
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
	
	/**
	 * 插入记录
	 * @param tableName
	 * @param userRecord
	 */
	/*
	 * public void insertRecord(
	 * 
	 * @Param("tableName")String tableName,
	 * 
	 * @Param("id")String id,
	 * 
	 * @Param("date")Date date,
	 * 
	 * @Param("cost")String cost,
	 * 
	 * @Param("type")String type );
	 */
	public void insertRecord(
			@Param("tableName")String tableName,
			@Param("userRecord")UserRecord userRecord
		); 
}