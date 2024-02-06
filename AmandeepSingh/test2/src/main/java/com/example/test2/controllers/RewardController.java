package com.example.test2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.test2.entities.*;
import com.example.test2.services.RewardService;

@RestController
class RewardPointsController {

	@Autowired
	RewardService rewardService;

	@GetMapping("/reward-report")
	public Map<String, Map<String, Integer>> getRewardPoints() {
		return rewardService.calculateRewards();

	}
	
	@GetMapping("/reward-report-with-dates")
	public List<RewardResponse> getRewardPointsBetweenDates(@RequestBody RewardRequest request) {
		return rewardService.getRewardPointsBetweenDates(request);

	}
	
	@PostMapping("/upload")
	public List<Transactions> upload(@RequestBody List<TransactionsDto> list)
	{
		return rewardService.upload(list);
	}
}
