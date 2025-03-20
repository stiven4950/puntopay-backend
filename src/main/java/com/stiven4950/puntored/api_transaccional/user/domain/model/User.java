package com.stiven4950.puntored.api_transaccional.user.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private long id;

    @NotNull
    @Size(max = 60)
    private String name;

    @NotNull
    @Size(max = 14)
    private String document;

    @NotNull
    @Pattern(regexp = "^3\\d{9}$", message = "El número de teléfono debe iniciar con '3', tener 10 dígitos y solo contener números")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener exactamente 10 dígitos")
    private String phone;

    @NotNull
    @Email
    private String email;

    @Size(min = 8, message = "La contraseña debe ser de al menos 8 caracteres")
    private String password;

    private Boolean locked;
    private Boolean disabled;
}
