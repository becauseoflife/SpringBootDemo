package com.bookkeeping.appdata.entity;

// 首页数据的实体类
public class HomePageData {

	private String todayCost;		// 本日消费
	
	private String monthCost;		// 本月消费 
	
	private String yearCost;		// 本年消费

	public String getTodayCost() {
		return todayCost;
	}

	public void setTodayCost(String todayCost) {
		this.todayCost = todayCost;
	}

	public String getMonthCost() {
		return monthCost;
	}

	public void setMonthCost(String monthCost) {
		this.monthCost = monthCost;
	}

	public String getYearCost() {
		return yearCost;
	}

	public void setYearCost(String yearCost) {
		this.yearCost = yearCost;
	}

}
