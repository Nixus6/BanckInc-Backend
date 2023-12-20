package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionCrudRepository extends ListCrudRepository<TransactionEntity,Integer> {
    List<TransactionEntity> findByIdTransaction(Integer transactionId);
    @Query(value =
            "UPDATE transaction " +
                    "SET state = 'CANCELLED' " +
                    "WHERE id_transaction = :idTransaction", nativeQuery = true)
    @Modifying
    void anulationTransaction(@Param("idTransaction") int trandactionId);
}
