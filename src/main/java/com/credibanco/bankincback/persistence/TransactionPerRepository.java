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

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionPerRepository implements TransactionRepository {
    @Autowired
    private TransactionCrudRepository transactionCrudRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.toTransactionEntity(transaction);
        return mapper.toTransaction(transactionCrudRepository.save(transactionEntity));
    }

    @Override
    public Optional<List<Transaction>> getTransaction(int cardId) {
        List<TransactionEntity> card = transactionCrudRepository.findByIdTransaction(cardId);
        return Optional.of(mapper.toTransactionsList(card));
    }

    @Override
    public void anulationTransaction(int transactionId) {
        transactionCrudRepository.anulationTransaction(transactionId);
    }
}
