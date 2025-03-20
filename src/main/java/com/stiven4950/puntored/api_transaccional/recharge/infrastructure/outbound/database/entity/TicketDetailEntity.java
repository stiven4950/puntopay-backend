package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_detail_id", nullable = false)
    private Long ticketDetailId;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false, length = 50)
    private String approval;

    @Column(nullable = false, length = 5)
    private String trace;

    @Column(nullable = false, length = 50)
    private String talon;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 10)
    private String value;

    @OneToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "id_ticket")
    private TicketEntity ticket;
}
