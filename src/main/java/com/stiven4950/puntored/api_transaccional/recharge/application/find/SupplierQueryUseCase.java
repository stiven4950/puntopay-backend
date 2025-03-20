package com.stiven4950.puntored.api_transaccional.recharge.application.find;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Supplier;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.SupplierQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierQueryUseCase {
    private final SupplierQueryRepository supplierQueryRepository;

    public List<Supplier> getSuppliers() {
        return this.supplierQueryRepository.getSuppliers();
    }
}