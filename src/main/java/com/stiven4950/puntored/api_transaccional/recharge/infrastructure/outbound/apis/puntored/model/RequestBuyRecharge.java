package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RequestBuyRecharge {
    private long pointOfSale;
    private long terminal;
    private String transactionalPassword;
    private String number;
    private long amount;
    private String trace;
    private String productCode;
}
