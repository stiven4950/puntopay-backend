package com.stiven4950.puntored.api_transaccional.recharge.infrastructure.inbound.controllers;

import com.stiven4950.puntored.api_transaccional.recharge.application.create.RechargeCommandUseCase;
import com.stiven4950.puntored.api_transaccional.recharge.application.dto.BuyRechargeDTO;
import com.stiven4950.puntored.api_transaccional.recharge.application.find.SupplierQueryUseCase;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Supplier;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseBuyRecharge;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recharge")
@RequiredArgsConstructor
public class RechargeController {
    private final SupplierQueryUseCase supplierQueryUseCase;
    private final RechargeCommandUseCase rechargeCommandUseCase;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        return ResponseEntity.ok(this.supplierQueryUseCase.getSuppliers());
    }

    @PostMapping("/buy")
    public ResponseEntity<ResponseBuyRecharge> buy(@Valid @RequestBody BuyRechargeDTO recharge){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        ResponseBuyRecharge response = this.rechargeCommandUseCase.buy(recharge, authentication.getPrincipal().toString());
        if (response.getState()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body(response);
    }
}
