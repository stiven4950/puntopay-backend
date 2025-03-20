package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.repository;

import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TransactionEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TransactionRepository extends ListCrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByUser_Email(String email);
}
