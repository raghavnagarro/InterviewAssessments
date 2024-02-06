package com.example.test2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transactions {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String transactionDate;
	private Double transactionValue;
	private Integer monthNumber;

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

	public String getDate() {
		return transactionDate;
	}

	public void setDate(String date) {
		this.transactionDate = date;
	}

	public Double getValue() {
		return transactionValue;
	}

	public void setValue(Double value) {
		this.transactionValue = value;
	}

	public Integer getMonth() {
		return monthNumber;
	}

	public void setMonth(Integer month) {
		this.monthNumber = month;
	}

	public Transactions(Long id, String name, String date, Double value, Integer month) {
		this.id = id;
		this.name = name;
		this.transactionDate = date;
		this.transactionValue = value;
		this.monthNumber = month;
	}

	public Transactions() {
	}
}