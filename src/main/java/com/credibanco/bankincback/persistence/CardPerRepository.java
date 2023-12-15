package com.credibanco.bankincback.persistence;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;
import com.credibanco.bankincback.persistence.crud.CardCrudRepository;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void activateCard(Long cardId) {
        cardCrudRepository.activateCard(cardId);
    }

    @Override
    public void blockCard(Long cardId) {
        cardCrudRepository.blockCard(cardId);
    }

    @Override
    public void rechargeBalance(Integer balance,Long cardId) {
        cardCrudRepository.rechargeBalance(balance, cardId);
    }
    @Override
    public Optional<List<Card>> checkBalance(Long cardId) {
        List<CardEntity> card = cardCrudRepository.findByIdCard(cardId);
        return Optional.of(mapper.toCards(card));
    }

}
