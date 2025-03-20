package com.stiven4950.puntored.api_transaccional.recharge.infrastructure;

import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Ticket;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.TicketDetail;
import com.stiven4950.puntored.api_transaccional.recharge.domain.model.Transaction;
import com.stiven4950.puntored.api_transaccional.recharge.domain.repository.TransactionQueryRepository;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TicketDetailEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TicketEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.entity.TransactionEntity;
import com.stiven4950.puntored.api_transaccional.recharge.infrastructure.outbound.database.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionQueryRepositoryImpl implements TransactionQueryRepository {
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactionsByUser(String username) {
        List<TransactionEntity> transactionsEntity = this.transactionRepository.findAllByUser_Email(username);

        return transactionsEntity.stream().
                map(transaction -> {
                    Transaction newTransaction = Transaction.builder().
                            date(transaction.getDate().toString()).
                            message(transaction.getMessage()).
                            transactionId(transaction.getIdTransaction()).
                            authorizationCode(transaction.getAuthorizationCode()).
                            trace(transaction.getTrace()).
                            build();

                    if (transaction.getTicket() != null){
                        TicketEntity ticketEntity = transaction.getTicket();
                        TicketDetailEntity detailEntity = ticketEntity.getBody();

                        TicketDetail ticketDetail = TicketDetail.builder().
                                date(detailEntity.getDate()).
                                time(detailEntity.getTime()).
                                approval(detailEntity.getApproval()).
                                trace(detailEntity.getTrace()).
                                talon(detailEntity.getTalon()).
                                number(detailEntity.getNumber()).
                                value(detailEntity.getValue()).
                                build();

                        Ticket ticket = Ticket.builder().
                                header(ticketEntity.getHeader()).
                                title(ticketEntity.getTitle()).
                                footer(ticketEntity.getFooter()).
                                body(ticketDetail).
                                logo(ticketEntity.getLogo()).
                                build();
                        newTransaction.setTicket(ticket);
                    }

                            return newTransaction;
                }).toList();
    }
}
