package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, length = 255)
    private String message;

    @Column(name = "authorization_code", nullable = false, length = 50)
    private String authorizationCode;

    @Column(nullable = false, length = 50)
    private String trace;

    @Column (name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id_ticket", nullable = true)
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity user;
}
