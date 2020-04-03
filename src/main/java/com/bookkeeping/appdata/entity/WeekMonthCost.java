package com.bookkeeping.appdata.entity;

// 本周消费和本月消费的实体类
public class WeekMonthCost {

	private String weekCost;	// 本周消费
		
	private String monthCost;	// 本月消费

	public String getWeekCost() {
		return weekCost;
	}

	public void setWeekCost(String weekCost) {
		this.weekCost = weekCost;
	}

	public String getMonthCost() {
		return monthCost;
	}

	public void setMonthCost(String monthCost) {
		this.monthCost = monthCost;
	}

}
