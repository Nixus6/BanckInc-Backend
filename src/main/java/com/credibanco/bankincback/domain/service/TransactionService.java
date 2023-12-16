package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.domain.repository.CardRepository;
import com.credibanco.bankincback.domain.repository.TransactionRepository;
import com.credibanco.bankincback.persistence.entity.TransactionState;
import com.credibanco.bankincback.persistence.entity.TypeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction purchaseTransaction(Transaction transaction){
        Transaction transactionCreate = Transaction.builder().totalPrice(transaction.getTotalPrice()).state(TransactionState.SUCCESSFUL).cardId(transaction.getCardId()).transactionDate(LocalDateTime.now()).build();
        return this.transactionRepository.purchaseTransaction(transactionCreate);
    }
    public Optional<List<Transaction>> getTransaction(int cardId) {
        return this.transactionRepository.getTransaction(cardId);
    }
}
