package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.persistence.entity.CardEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CardCrudRepository extends ListCrudRepository<CardEntity,Integer>{


}
