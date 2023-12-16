package com.credibanco.bankincback.domain.repository;

import com.credibanco.bankincback.domain.Transaction;

public interface TransactionRepository {
    Transaction purchaseTransaction(Transaction transaction);
}
