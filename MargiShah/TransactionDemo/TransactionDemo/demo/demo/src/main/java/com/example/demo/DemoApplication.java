package com.example.demo;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
/*
WebAPI Developer
A retailer offers a rewards program to its customers,
awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over
$100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

·         Solve using Spring Boot
·         Create a RESTful endpoint
·         Make up a data set to best demonstrate your solution
* */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TransactionService transactionService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Transaction>> typeReference = new TypeReference<List<Transaction>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/templates/DataFile.json");
			try {
				List<Transaction> transactions = mapper.readValue(inputStream,typeReference);
				transactionService.save(transactions);
				System.out.println("Transaction Saved!");
			} catch (IOException e){
				System.out.println("Unable to save transaction: " + e.getMessage());
			}
		};
	}

}
