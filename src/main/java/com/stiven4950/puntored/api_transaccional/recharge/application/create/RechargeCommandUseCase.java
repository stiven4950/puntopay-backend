package com.stiven4950.puntored.api_transaccional.recharge.application.create;

import com.stiven4950.puntored.api_transaccional.recharge.application.dto.BuyRechargeDTO;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.RechargeCommandRepository;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.RequestBuyRecharge;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseBuyRecharge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RechargeCommandUseCase {
    private final RechargeCommandRepository rechargeCommandRepository;

    public ResponseBuyRecharge buy(BuyRechargeDTO request, String username){
        return this.rechargeCommandRepository.buy(request, username);
    }
}
