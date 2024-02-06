package com.example.test2.entities;

public class RewardResponse {
	private String customerName;
	private int totalTransactions;
	private String startDate;
	private String endDate;
	private int rewardPoints;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(int totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	@Override
	public String toString() {
		return "RewardResponse [customerName=" + customerName + ", totalTransactions=" + totalTransactions
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", rewardPoints=" + rewardPoints + "]";
	}
	public RewardResponse(String customerName, int totalTransactions, String startDate, String endDate,
			int rewardPoints) {
		super();
		this.customerName = customerName;
		this.totalTransactions = totalTransactions;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rewardPoints = rewardPoints;
	}
	public RewardResponse() {
		super();
	}

}
