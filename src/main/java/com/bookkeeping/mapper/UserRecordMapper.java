package com.bookkeeping.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bookkeeping.appdata.entity.PieChartSeries;
import com.bookkeeping.appdata.entity.RecordData;
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
	 * 查找年消费
	 * @param tableName
	 * @return
	 */
	public String getYearSumCost(@Param("tableName")String tableName);
	
	/**
	 * 获取月消费
	 * @param tableName
	 * @return
	 */
	public String getMonthSumCost(@Param("tableName")String tableName);
	
	/**
	 * 获取日消费
	 * @param tableName
	 * @return sum(cost)
	 */
	public String getDaySumCost(@Param("tableName")String tableName);
	
	/**
	 * 查找历史记录
	 * @param tableName
	 * @return
	 */
	public List<RecordData> queryRecords(@Param("tableName")String tableName);
	
	/**
	 * 统计类型的数量
	 * @return
	 */
	public List<PieChartSeries> queryTypeSum(
			@Param("tableName")String tableName,
			@Param("typeArray")String[] typeArray
			);
	
	
	
	
}