package com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleId implements Serializable {
    private Long idUser;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, role);
    }
}
