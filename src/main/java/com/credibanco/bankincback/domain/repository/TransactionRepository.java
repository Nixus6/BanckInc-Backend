package com.credibanco.bankincback.domain.repository;

import com.credibanco.bankincback.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<List<Transaction>> getTransaction(int cardId);
    void anulationTransaction(int transactionId);
}
