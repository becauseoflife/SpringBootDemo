package com.bookkeeping.pojo;

import java.sql.Date;

public class UserBookkeepingMsg {

	private String id;				// 用户账号
	private Date date;				// 日期时间
	private Double cost;			// 花费金额
	private String type;			// 种类
	private Double hopeSave; 		// 心愿存钱
	private Double week_max_cost;	// 本周最大额度
	private Double month_max_cost;	// 本月最大额度
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getHopeSave() {
		return hopeSave;
	}
	public void setHopeSave(Double hopeSave) {
		this.hopeSave = hopeSave;
	}
	public Double getWeek_max_cost() {
		return week_max_cost;
	}
	public void setWeek_max_cost(Double week_max_cost) {
		this.week_max_cost = week_max_cost;
	}
	public Double getMonth_max_cost() {
		return month_max_cost;
	}
	public void setMonth_max_cost(Double month_max_cost) {
		this.month_max_cost = month_max_cost;
	}

}
