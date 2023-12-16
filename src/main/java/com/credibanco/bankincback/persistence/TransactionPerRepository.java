package com.credibanco.bankincback.persistence;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.domain.repository.TransactionRepository;
import com.credibanco.bankincback.persistence.crud.TransactionCrudRepository;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.entity.TransactionEntity;
import com.credibanco.bankincback.persistence.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionPerRepository implements TransactionRepository {
    @Autowired
    private TransactionCrudRepository transactionCrudRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public Transaction purchaseTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.toTransactionEntity(transaction);
        return mapper.toTransaction(transactionCrudRepository.save(transactionEntity));
    }
}
