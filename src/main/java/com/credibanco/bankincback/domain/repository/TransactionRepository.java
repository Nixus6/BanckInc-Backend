package com.credibanco.bankincback.domain.repository;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction purchaseTransaction(Transaction transaction);
    Optional<List<Transaction>> getTransaction(int cardId);
}
