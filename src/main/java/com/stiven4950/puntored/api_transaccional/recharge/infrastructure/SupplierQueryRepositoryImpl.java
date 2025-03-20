package com.stiven4950.puntored.api_transaccional.recharge.infrastructure;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Supplier;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.SupplierQueryRepository;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.client.PuntoredRechargeAPIClient;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.RequestSupplier;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SupplierQueryRepositoryImpl implements SupplierQueryRepository {
    private final PuntoredRechargeAPIClient rechargeApiClient;
    private final Environment env;

    @Override
    public List<Supplier> getSuppliers() {
        String pointOfSale = this.env.getProperty("puntored.puntoVenta");
        long pointOfSaleConverted = Long.parseLong(pointOfSale != null ? pointOfSale : "");

        RequestSupplier request = RequestSupplier.builder().pointOfSale(pointOfSaleConverted).build();
        ResponseSupplier response = this.rechargeApiClient.getSuppliers(request);

        return response.getData().getSuppliers();
    }
}
