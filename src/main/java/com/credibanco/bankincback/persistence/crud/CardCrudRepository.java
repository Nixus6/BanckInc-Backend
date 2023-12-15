package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardCrudRepository extends ListCrudRepository<CardEntity,Long>{
    List<CardEntity> findByIdCard(Long cardId);
    @Query(value =
            "UPDATE card " +
                    "SET balance = :balance " +
            "WHERE id_card = :idCard", nativeQuery = true)
    @Modifying
    void rechargeBalance(@Param("balance") Integer balance, @Param("idCard") Long cardId);
    @Query(value =
            "UPDATE card " +
                    "SET state = true " +
                    "WHERE id_card = :idCard", nativeQuery = true)
    @Modifying
    void activateCard(@Param("idCard") Long cardId);

    @Query(value =
            "UPDATE card " +
                    "SET state = false " +
                    "WHERE id_card = :idCard", nativeQuery = true)
    @Modifying
    void blockCard(@Param("idCard") Long cardId);
}
