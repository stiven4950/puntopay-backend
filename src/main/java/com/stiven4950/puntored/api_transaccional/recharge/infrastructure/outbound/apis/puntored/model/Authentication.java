package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model;

import lombok.*;

@Getter
@Setter
@Builder
public class Authentication {
    private String username;
    private String password;
    private long commerce;
}
