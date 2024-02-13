package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    public int calculateReward(String customerName) throws Exception {
        List<Transaction> transactionList = transactionRepository.findAll();
        this.logger.info("Total transaction found:" +transactionList.size());

        List<String> transactionValues = transactionList.stream()
                    .filter(a->a.getCustomerName().equals(customerName))
                    .map(Transaction::getTransactionValue).toList();
        this.logger.info("Total transaction found with customer name:" +transactionValues.size());

        if(transactionValues.size()<=0){
            throw new Exception("No Record found for: "+customerName);
        }
        int total = 0;
        for(String transactionVal:transactionValues){
            this.logger.info("transactionVal before parsing:" +transactionVal);
            String result =  transactionVal.replace("$", "").replace(",", "");
            this.logger.info("transactionVal after parsing:" +result);
            total += Double.valueOf(result);
        }

        int rewards = 0;
        if(total>=100) {
            rewards += ((total%100)+2);
            total = total%100;
        }

        if(total>=50 && total<100) {
            rewards = ((total%50)+1);
            total = total%50;
        }
        return rewards;
    }

    public void save(List<Transaction> transactions) {
        for(Transaction tr:transactions)
            transactionRepository.save(tr);
    }
}
