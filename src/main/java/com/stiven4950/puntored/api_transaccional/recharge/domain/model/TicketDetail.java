package com.stiven4950.puntored.api_transaccional.recharge.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TicketDetail {
    @JsonProperty("Fecha")
    private String date;

    @JsonProperty("Hora")
    private String time;

    @JsonProperty("Aprobacion")
    private String approval;

    @JsonProperty("Trace")
    private String trace;

    @JsonProperty("Talon")
    private String talon;

    @JsonProperty("Numero")
    private String number;

    @JsonProperty("Valor")
    private String value;
}
