package com.stiven4950.puntored.api_transaccional.user.application.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
