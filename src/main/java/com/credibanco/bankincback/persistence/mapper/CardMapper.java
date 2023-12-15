package com.credibanco.bankincback.persistence.mapper;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mappings({
            @Mapping(source="idCard", target = "cardId"),
            @Mapping(source="expirationDate", target = "expiration")
    })
    Card toCard(CardEntity cardEntity);
    List<Card> toCards(List<CardEntity> cardEntities);
    @InheritInverseConfiguration
    @Mapping(target = "transactions", ignore = true)
    CardEntity toCardEntity(Card card);
}
