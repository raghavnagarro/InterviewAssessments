package com.example.test2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test2.entities.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
	@Query("select t from Transactions t where SUBSTRING(t.transactionDate, 1, 4) || '-' || SUBSTRING(t.transactionDate, 5, 2) || '-' || SUBSTRING(t.transactionDate, 7, 2) between ?1 and ?2")
	  List<Transactions> findByTransactionDateStringBetween(String startDate, String endDate);
}
