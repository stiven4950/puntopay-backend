package com.stiven4950.puntored.api_transaccional.recharge.application.find;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.TransactionQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsQueryUseCase {
    private final TransactionQueryRepository transactionQueryRepository;

    public List<Transaction> findTransactionsByUser(String username) {
        return this.transactionQueryRepository.findTransactionsByUser(username);
    }
}