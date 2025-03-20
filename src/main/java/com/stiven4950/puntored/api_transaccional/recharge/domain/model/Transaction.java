package com.stiven4950.puntored.api_transaccional.recharge.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Transaction {
    private long transactionId;
    private String date;
    private Ticket ticket;
    private String message;
    private String authorizationCode;
    private String trace;
}
