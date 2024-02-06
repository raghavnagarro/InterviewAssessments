package com.example.test2.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test2.entities.RewardRequest;
import com.example.test2.entities.RewardResponse;
import com.example.test2.entities.Transactions;
import com.example.test2.entities.TransactionsDto;
import com.example.test2.repositories.TransactionRepository;

@Service
public class RewardService {

	@Autowired
	TransactionRepository transactionRepository;

	public Map<String, Map<String, Integer>> calculateRewards() {

		Map<String, Map<String, Integer>> rewards = new HashMap<>();
		try {
			List<Transactions> transactions = transactionRepository.findAll();

			for (Transactions transaction : transactions) {
				String name = transaction.getName();
				Integer month = transaction.getMonth();
				String monthString = month.toString();
				int points = calculatePoints(transaction);
				if (!rewards.containsKey(name)) {
					rewards.put(name, new HashMap<>());
				}
				Map<String, Integer> customerRewards = rewards.get(name);
				if (!customerRewards.containsKey(monthString)) {
					customerRewards.put(monthString, 0);
				}
				int currentPoints = customerRewards.get(monthString);
				int newPoints = currentPoints + points;
				customerRewards.put(monthString, newPoints);
				if (!customerRewards.containsKey("total")) {
					customerRewards.put("total", 0);
				}
				int currentTotal = customerRewards.get("total");
				int newTotal = currentTotal + points;
				customerRewards.put("total", newTotal);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return rewards;
	}

	private int calculatePoints(Transactions transaction) {
		int points = 0;
		double value = transaction.getValue();
		points += value > 100 ? 2 * (value - 100) + 50 : value > 50 ? value - 50 : 0;
		return points;
	}

	public List<Transactions> upload(List<TransactionsDto> list) {
		List<Transactions> entities = convertDtoToEntity(list);
		return transactionRepository.saveAll(entities);
	}

	private List<Transactions> convertDtoToEntity(List<TransactionsDto> dtos) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<Transactions> entities = new ArrayList<>();
		for (TransactionsDto dto : dtos) {
			LocalDate date = LocalDate.parse(dto.getTransactionDate(), formatter);
			int monthNumber = date.getMonthValue();
			Transactions entity = new Transactions(dto.getId(), dto.getName(), dto.getTransactionDate(),
					dto.getTransactionValue(), monthNumber);
			entities.add(entity);
		}
		return entities;
	}

	public List<RewardResponse> getRewardPointsBetweenDates(RewardRequest request) {
		List<Transactions> transactions = transactionRepository
				.findByTransactionDateStringBetween(request.getStartDate(), request.getEndDate());
		List<RewardResponse> response = new ArrayList<>();
		Map<String, String> custPointsMap = new HashMap<>();
		for (Transactions transaction : transactions) {
			String name = transaction.getName();
			int points = calculatePoints(transaction);
			if (!custPointsMap.containsKey(name)) {
				custPointsMap.put(name, "1:" + points);
			} else {
				int txnCount = Integer.parseInt(custPointsMap.get(name).split(":")[0]) + 1;
				int tmpPoints = Integer.parseInt(custPointsMap.get(name).split(":")[1]) + points;
				custPointsMap.put(name, txnCount + ":" + tmpPoints);
			}
		}
		for (Map.Entry<String, String> entry : custPointsMap.entrySet()) {
			RewardResponse obj = new RewardResponse();
			obj.setCustomerName(entry.getKey());
			obj.setStartDate(request.getStartDate());
			obj.setEndDate(request.getEndDate());
			int txnCount = Integer.parseInt(entry.getValue().split(":")[0]);
			int tmpPoints = Integer.parseInt(entry.getValue().split(":")[1]);
			obj.setTotalTransactions(txnCount);
			obj.setRewardPoints(tmpPoints);
			response.add(obj);

		}
		return response;
	}

}
