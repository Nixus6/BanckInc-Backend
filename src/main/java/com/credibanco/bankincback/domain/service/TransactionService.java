package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.domain.repository.CardRepository;
import com.credibanco.bankincback.domain.repository.TransactionRepository;
import com.credibanco.bankincback.persistence.entity.TransactionState;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository= cardRepository;
    }

    @Transactional
    public Transaction purchaseTransaction(Card card,Transaction transaction){
        try {
            //Se descuenta el valor de la compra
            int newBalance = card.getBalance() - transaction.getPrice();
            this.cardRepository.rechargeBalance(newBalance, card.getCardId());
            //Se crea la nueva transacción
            Transaction transactionCreate = Transaction.builder().price(transaction.getPrice()).state(TransactionState.SUCCESSFUL).cardId(transaction.getCardId()).transactionDate(LocalDateTime.now()).build();
            return this.transactionRepository.save(transactionCreate);
        }catch (DataAccessException e) {
            log.error("Error de acceso a datos al realizar la transacción de compra", e);
            throw new TransactionException("Error al procesar la transacción de compra", e);
        }catch (Exception e) {
            log.error("Error inesperado al realizar la transacción de compra", e);
            throw new RuntimeException("Error inesperado al procesar la transacción de compra", e);
        }
    }

    public Optional<List<Transaction>> getTransaction(int cardId) {
        return this.transactionRepository.getTransaction(cardId);
    }

    @Transactional
    public void anulationTransaction(Transaction transaction, Card card){
        try {
            int newBalance = card.getBalance() + transaction.getPrice();
            this.cardRepository.rechargeBalance(newBalance, card.getCardId());
            this.transactionRepository.anulationTransaction(transaction.getTransactionId());
        } catch (DataAccessException e) {
            // Manejar problemas de acceso a datos (por ejemplo, problemas al interactuar con la base de datos)
            log.error("Error de acceso a datos al anular la transacción", e);
            throw new TransactionException("Error al procesar la anulación de la transacción", e);
        } catch (Exception e) {
            // Capturar excepciones generales (último recurso)
            log.error("Error inesperado al anular la transacción", e);
            throw new RuntimeException("Error inesperado al procesar la anulación de la transacción", e);
        }
    }
}
