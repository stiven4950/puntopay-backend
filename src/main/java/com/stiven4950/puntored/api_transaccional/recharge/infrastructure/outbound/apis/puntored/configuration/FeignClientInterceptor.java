package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.configuration;

import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.service.TokenService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientInterceptor {
    private final TokenService tokenService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = this.tokenService.getToken();
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
