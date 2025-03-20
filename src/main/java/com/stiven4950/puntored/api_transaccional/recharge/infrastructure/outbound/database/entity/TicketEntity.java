package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", nullable = false)
    private Long idTicket;

    @Column(nullable = false, length = 255)
    private String header;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 1200)
    private String footer;

    @Column(nullable = false, length = 255)
    private String logo;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private TicketDetailEntity body;

    public void setBody(TicketDetailEntity body) {
        this.body = body;
        body.setTicket(this);
    }
}
