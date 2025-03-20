package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBuyRecharge {
    private Boolean state;
    private String message;
    private String code;
    private Transaction data;
}
