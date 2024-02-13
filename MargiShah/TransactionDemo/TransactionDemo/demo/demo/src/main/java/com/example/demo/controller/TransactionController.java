package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {
    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @GetMapping("/getReward/{customerName}")
    public ResponseEntity<String> getReward(@PathVariable String customerName) {
        this.logger.info("Find Reward for customer name:" +customerName);
        try {
            return ResponseEntity.ok("Rewards for customer "+customerName+" is : "+String.valueOf(transactionService.calculateReward(customerName)));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
