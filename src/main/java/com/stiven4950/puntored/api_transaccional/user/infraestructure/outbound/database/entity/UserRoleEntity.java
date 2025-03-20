package com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleEntity {
    @Id
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "granted_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity user;
}
