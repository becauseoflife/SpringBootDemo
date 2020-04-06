package com.bookkeeping.appdata.entity;

// 首页数据的实体类
public class HomePageData {

	private String todayCost;		// 本日消费
	
	private String monthCost;		// 本月消费 
	
	private String yearCost;		// 本年消费
	
	private String todayIncome;		// 本日收入
	
	private String monthIncome;     // 本月收入
	
	private String yearIncome;		// 本年收入

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

	public String getTodayIncome() {
		return todayIncome;
	}

	public void setTodayIncome(String todayIncome) {
		this.todayIncome = todayIncome;
	}

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}

}
