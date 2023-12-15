package com.credibanco.bankincback.persistence;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;
import com.credibanco.bankincback.persistence.crud.CardCrudRepository;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardPerRepository implements CardRepository {
    @Autowired
    private CardCrudRepository cardCrudRepository;

    @Autowired
    private CardMapper mapper;

    @Override
    public Card save(Card card) {
        CardEntity cardEntity = mapper.toCardEntity(card);
        return mapper.toCard(cardCrudRepository.save(cardEntity));
    }
}
