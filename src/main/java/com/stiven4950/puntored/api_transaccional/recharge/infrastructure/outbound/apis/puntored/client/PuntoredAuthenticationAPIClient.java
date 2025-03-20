package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.client;


import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.Authentication;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseAuthentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "puntored-authentication", url = "${puntored.endpoint}")
public interface PuntoredAuthenticationAPIClient {
    @PostMapping("/auth")
    ResponseAuthentication auth(Authentication login);
}
