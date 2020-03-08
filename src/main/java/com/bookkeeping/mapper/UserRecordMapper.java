package com.bookkeeping.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
	public void insertRecord(
			@Param("tableName")String tableName,
			@Param("userRecord")UserRecord userRecord
		); 
	
	/**
	 * 查找年消费值
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public List<String> queryYearCost(
			@Param("tableName")String tableName,
			@Param("yearFieldName")String yearFieldName,
			@Param("yearFieldValue")String yearFieldValue
		);
	
	/**
	 * 查找月消费值
	 * @param tableName
	 * @param yearFieldName
	 * @param yearFieldValue
	 * @param monthFieldName
	 * @param monthFieldValue
	 * @return
	 */
	public List<String> queryMonthCost(
			@Param("tableName")String tableName,
			@Param("yearFieldName")String yearFieldName,
			@Param("yearFieldValue")String yearFieldValue,
			@Param("monthFieldName")String monthFieldName,
			@Param("monthFieldValue")String monthFieldValue
		);
	
	/**
	 * 查找日消费值
	 * @param tableName
	 * @param yearFieldName
	 * @param yearFieldValue
	 * @param monthFieldName
	 * @param monthFieldValue
	 * @param todayFieldName
	 * @param todayFieldValue
	 * @return
	 */
	public List<String> queryTodayCost(
			@Param("tableName")String tableName,
			@Param("yearFieldName")String yearFieldName,
			@Param("yearFieldValue")String yearFieldValue,
			@Param("monthFieldName")String monthFieldName,
			@Param("monthFieldValue")String monthFieldValue,
			@Param("todayFieldName")String todayFieldName,
			@Param("todayFieldValue")String todayFieldValue
		);
	
}