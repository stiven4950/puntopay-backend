package com.stiven4950.puntored.api_transaccional.recharge.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuyRechargeDTO {
    @Pattern(regexp = "^3\\d{9}$", message = "El número de teléfono debe iniciar con '3', tener 10 dígitos y solo contener números")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener exactamente 10 dígitos")
    private String number;

    @Min(value = 1000, message = "El monto mínimo es de $ 1.000")
    @Max(value = 100000, message = "El monto máximo es de $ 100.000")
    private long amount;

    private String productCode;
}
