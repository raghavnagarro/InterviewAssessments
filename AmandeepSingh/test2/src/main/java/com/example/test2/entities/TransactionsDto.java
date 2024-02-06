package com.example.test2.entities;

public class TransactionsDto {
	private Long id;
	private String name;
	private String transactionDate;
	private Double transactionValue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getTransactionValue() {
		return transactionValue;
	}
	public void setTransactionValue(Double transactionValue) {
		this.transactionValue = transactionValue;
	}
	@Override
	public String toString() {
		return "TransactionsDto [id=" + id + ", name=" + name + ", transactionDate=" + transactionDate
				+ ", transactionValue=" + transactionValue + "]";
	}
	public TransactionsDto(Long id, String name, String transactionDate, Double transactionValue) {
		super();
		this.id = id;
		this.name = name;
		this.transactionDate = transactionDate;
		this.transactionValue = transactionValue;
	}
	public TransactionsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
