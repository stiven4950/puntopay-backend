package com.stiven4950.puntored.api_transaccional.recharge.infrastructure;

import com.stiven4950.puntored.api_transaccional.recharge.application.dto.BuyRechargeDTO;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Ticket;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.TicketDetail;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.RechargeCommandRepository;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.client.PuntoredRechargeAPIClient;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.RequestBuyRecharge;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.apis.puntored.model.ResponseBuyRecharge;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TicketDetailEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TicketEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TransactionEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.repository.TransactionRepository;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.entity.UserEntity;
import com.stiven4950.puntored.api_transaccional.user.infraestructure.outbound.database.repository.UserRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class RechargeCommandRepositoryImpl implements RechargeCommandRepository {
    private final PuntoredRechargeAPIClient rechargeAPIClient;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final Environment env;

    @Override
    public ResponseBuyRecharge buy(BuyRechargeDTO recharge, String username) {
        ResponseBuyRecharge response = new ResponseBuyRecharge();

        try {
            String pointOfSale = this.env.getProperty("puntored.puntoVenta");
            String terminal = this.env.getProperty("puntored.terminal");
            String transactionalPassword = this.env.getProperty("puntored.claveTransaccional");

            if (pointOfSale == null || terminal == null || transactionalPassword == null) {
                throw new IllegalArgumentException("Las propiedades de configuración son inválidas");
            }

            long pointOfSaleConverted = Long.parseLong(pointOfSale);
            long terminalConverted = Long.parseLong(terminal);

            Long trace = this.transactionRepository.count();

            RequestBuyRecharge request = RequestBuyRecharge.builder()
                    .pointOfSale(pointOfSaleConverted)
                    .terminal(terminalConverted)
                    .transactionalPassword(transactionalPassword)
                    .number(recharge.getNumber())
                    .amount(recharge.getAmount())
                    .trace(trace.toString())
                    .productCode(recharge.getProductCode())
                    .build();

            try {
                response = this.rechargeAPIClient.buy(request);
            } catch (FeignException e) {
                response.setMessage("Error en la API externa: " + e.getMessage());
                response.setState(false);
                return response;
            }

            if (response.getState()) {
                UserEntity userEntity = userRepository.findFirstByEmail(username)
                        .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

                Transaction transaction = response.getData();
                Ticket ticket = transaction.getTicket();
                TicketDetail ticketDetail = ticket.getBody();

                TicketDetailEntity ticketDetailEntity = TicketDetailEntity.builder()
                        .date(ticketDetail.getDate())
                        .time(ticketDetail.getTime())
                        .approval(ticketDetail.getApproval())
                        .trace(ticketDetail.getTrace())
                        .talon(ticketDetail.getTalon())
                        .number(ticketDetail.getNumber())
                        .value(ticketDetail.getValue())
                        .build();

                TicketEntity ticketEntity = TicketEntity.builder()
                        .header(ticket.getHeader())
                        .title(ticket.getTitle())
                        .footer(ticket.getFooter())
                        .logo(ticket.getLogo())
                        .build();
                ticketEntity.setBody(ticketDetailEntity);

                OffsetDateTime offsetDateTime = OffsetDateTime.parse(transaction.getDate());
                LocalDateTime dateTransaction = offsetDateTime.toLocalDateTime();

                TransactionEntity newTransactionEntity = TransactionEntity.builder()
                        .idTransaction(transaction.getTransactionId())
                        .date(dateTransaction)
                        .message(transaction.getMessage())
                        .authorizationCode(transaction.getAuthorizationCode())
                        .trace(transaction.getTrace())
                        .ticket(ticketEntity)
                        .build();

                newTransactionEntity.setUserId(userEntity.getIdUser());

                transactionRepository.save(newTransactionEntity);
            }

        } catch (NumberFormatException e) {
            response.setMessage("Error al convertir valores numéricos: " + e.getMessage());
            response.setState(false);
        } catch (NoSuchElementException e) {
            response.setMessage("Error: " + e.getMessage());
            response.setState(false);
        } catch (Exception e) {
            response.setMessage("Error inesperado: " + e.getMessage());
            response.setState(false);
        }

        return response;
    }
}
