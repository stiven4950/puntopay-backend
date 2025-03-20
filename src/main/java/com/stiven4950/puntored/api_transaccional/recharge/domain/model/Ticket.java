package com.stiven4950.puntored.api_transaccional.recharge.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ticket {
    private String header;
    private String title;
    private TicketDetail body;
    private String footer;
    private String logo;
}
