package com.stiven4950.puntored.api_transaccional.recharge.domain.repository;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Supplier;

import java.util.List;

public interface SupplierQueryRepository {
    List<Supplier> getSuppliers();
}