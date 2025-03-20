package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.inbound.controllers;

import com.stiven4950.puntored.api_transaccional.recharge.application.find.TransactionsQueryUseCase;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Supplier;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionsQueryUseCase transactionsQueryUseCase;

    @GetMapping
    public ResponseEntity<List<Transaction>> getHistoryTransactions() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            List<Transaction> transactions = this.transactionsQueryUseCase.findTransactionsByUser(authentication.getPrincipal().toString());
            return ResponseEntity.ok(transactions);
        }
        return ResponseEntity.badRequest().build();
    }
}
