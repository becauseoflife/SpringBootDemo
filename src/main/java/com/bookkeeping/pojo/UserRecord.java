package com.bookkeeping.pojo;

import java.util.Date;

public class UserRecord {

	private String id;				// 用户账号
	private Date date;				// 日期时间
	private String cost;			// 花费金额
	private String type;			// 种类
	
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
