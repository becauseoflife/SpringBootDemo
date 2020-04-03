package com.bookkeeping.appdata.entity;

import javax.persistence.Column;
import javax.persistence.Table;

// 本周和月最大消费的数据模型
@Table(name = "user_info")
public class LimitCostData {
	
	@Column(name = "week_max_cost")
	private double weekMaxCost;		// 周最大消费额度
	
	@Column(name = "month_max_cost")
	private double monthMaxCost;	// 月最大消费额度

	public double getWeekMaxCost() {
		return weekMaxCost;
	}

	public void setWeekMaxCost(double weekMaxCost) {
		this.weekMaxCost = weekMaxCost;
	}

	public double getMonthMaxCost() {
		return monthMaxCost;
	}

	public void setMonthMaxCost(double monthMaxCost) {
		this.monthMaxCost = monthMaxCost;
	}
	
}
