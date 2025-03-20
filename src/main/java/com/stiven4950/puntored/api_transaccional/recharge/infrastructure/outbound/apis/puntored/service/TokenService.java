package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.service;

import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.client.PuntoredAuthenticationAPIClient;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.Authentication;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseAuthentication;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.TokenData;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final PuntoredAuthenticationAPIClient authClient;
    private final Environment environment;

    public synchronized String getToken() {
        try {
            String username = environment.getProperty("puntored.usuarioHost");
            String password = environment.getProperty("puntored.claveHost");
            String commerce = environment.getProperty("puntored.comercio");
            long commerceConverted = Long.parseLong(commerce != null ? commerce : "");

            Authentication login = Authentication.builder().
                    username(username).
                    password(password).
                    commerce(commerceConverted).
                    build();

            ResponseAuthentication response = authClient.auth(login);
            return response.getData().getToken();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el token", e);
        }
    }
}