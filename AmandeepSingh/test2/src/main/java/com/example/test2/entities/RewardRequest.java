package com.example.test2.entities;


public class RewardRequest {
	private String startDate;
	private String endDate;
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
	@Override
	public String toString() {
		return "RewardRequest [startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public RewardRequest(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public RewardRequest() {
		super();
	}
}
