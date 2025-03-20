package com.stiven4950.puntored.api_transaccional.recharge.domain.repository;

import com.stiven4950.puntored.api_transaccional.recharge.application.dto.BuyRechargeDTO;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseBuyRecharge;

public interface RechargeCommandRepository {
    ResponseBuyRecharge buy(BuyRechargeDTO recharge, String username);
}
