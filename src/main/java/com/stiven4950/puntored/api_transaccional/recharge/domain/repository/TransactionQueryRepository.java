package com.stiven4950.puntored.api_transaccional.recharge.domain.repository;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;

import java.util.List;

public interface TransactionQueryRepository {
    List<Transaction> findTransactionsByUser(String username);
}
