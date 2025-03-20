package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.client;


import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.configuration.FeignClientInterceptor;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.RequestBuyRecharge;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.RequestSupplier;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseBuyRecharge;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseSupplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "puntored-suppliers", url = "${puntored.endpoint}", configuration = FeignClientInterceptor.class)
public interface PuntoredRechargeAPIClient {
    @PostMapping("/recharge/find-suppliers")
    ResponseSupplier getSuppliers(RequestSupplier request);

    @PostMapping("/recharge/buy")
    ResponseBuyRecharge buy(RequestBuyRecharge request);
}
