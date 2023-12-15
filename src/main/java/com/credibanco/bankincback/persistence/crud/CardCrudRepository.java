package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CardCrudRepository extends ListCrudRepository<CardEntity,Integer>{

    @Query(value =
            "UPDATE card " +
                    "SET state = true " +
                    "WHERE id_card = :idCard", nativeQuery = true)
    @Modifying
    void activateCard(@Param("idCard") Long cardId);

}
