package com.stiven4950.puntored.api_transaccional.user.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLogin {
    private String accessToken;
}
