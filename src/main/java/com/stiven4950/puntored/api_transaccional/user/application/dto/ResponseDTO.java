package com.stiven4950.puntored.api_transaccional.user.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private boolean state;
    private String message;
    private Integer code;
}
