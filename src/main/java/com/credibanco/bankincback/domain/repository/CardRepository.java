package com.credibanco.bankincback.domain.repository;

import com.credibanco.bankincback.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    void activateCard(Long cardId);
//    Optional<List<Card>> activateCard(int cardId);
//
//    void blockCard(int cardId);
}
