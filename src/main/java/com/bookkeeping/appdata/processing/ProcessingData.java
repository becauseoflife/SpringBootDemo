package com.bookkeeping.appdata.processing;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookkeeping.appdata.entity.HomePageData;
import com.bookkeeping.mapper.UserRecordMapper;

/**
 * 处理数据（目前未用到）
 * 
 * @author 17521
 *
 */
/*
 * @Component public class ProcessingData {
 * 
 * @Autowired private UserRecordMapper getRecordMapper;
 * 
 * // 从数据库中获取数据，并处理返回给微信小程序的主页使用 public HomePageData getHomePageData(String
 * tableName) {
 * 
 * Date nowDate = new Date(); String nowYear = String.format("%tY", nowDate);
 * String nowMonth = String.format("%tm", nowDate); String nowDay =
 * String.format("%td", nowDate);
 * 
 * 
 * List<String> yearCostList = getRecordMapper.queryCost(tableName, "year",
 * nowYear); List<String> monthCostList = getRecordMapper.queryCost(tableName,
 * "month", nowMonth); List<String> dayCostList =
 * getRecordMapper.queryCost(tableName, "day", nowDay);
 * 
 * 
 * Double yearCost = 0.0; Double monthCost = 0.0; Double dayCost = 0.0;
 * 
 * 
 * for (String cost:yearCostList) { yearCost += Double.parseDouble(cost); } for
 * (String cost:monthCostList) { monthCost += Double.parseDouble(cost); } for
 * (String cost:dayCostList) { dayCost += Double.parseDouble(cost); }
 * 
 * 
 * System.out.println("yearCost:" + yearCost + " monthCost:" + monthCost +
 * " dayCost:" + dayCost);
 * 
 * 
 * return null; } }
 */
