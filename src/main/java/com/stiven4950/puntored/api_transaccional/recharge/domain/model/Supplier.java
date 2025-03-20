package com.stiven4950.puntored.api_transaccional.recharge.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String supplierCode;
    private String supplierDescription;
    private String supplierLogo;
}
