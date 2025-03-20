package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSupplier {
    private boolean state;
    private String message;
    private String code;
    private  SupplierData data;
}