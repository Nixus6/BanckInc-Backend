package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.entity.TransactionEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TransactionCrudRepository extends ListCrudRepository<TransactionEntity,Integer> {
    List<TransactionEntity> findByIdTransaction(Integer transactionId   );
}
