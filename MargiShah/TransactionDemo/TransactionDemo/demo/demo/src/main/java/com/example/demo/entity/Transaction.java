package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    Integer id;
    String customerName;
    String transactionDate;
    String transactionId;
    String transactionValue;

    public Transaction(Integer id, String customerName, String transactionDate, String transactionId, String transactionValue) {
        this.id = id;
        this.customerName = customerName;
        this.transactionDate = transactionDate;
        this.transactionId = transactionId;
        this.transactionValue = transactionValue;
    }
}
