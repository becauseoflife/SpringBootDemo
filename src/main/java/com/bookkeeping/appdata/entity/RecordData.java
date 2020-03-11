package com.bookkeeping.appdata.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

// 记账历史记录实体类
public class RecordData {

	@JsonFormat(pattern="yyyy-MM-dd hh:mm", locale="zh", timezone="GMT+8")
	private Date date;		// 日期时间
	
	private String cost;	// 花费
	
	private String type;	// 种类
	
	private String imgPath;	// 种类的图标路径

	
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
