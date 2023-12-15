package com.credibanco.bankincback.domain.repository;

import com.credibanco.bankincback.domain.Card;

public interface CardRepository {
    Card save(Card card);
    void activateCard(Long cardId);
    void blockCard(Long cardId);
    void rechargeBalance(Integer balance, Long cardId);

}
