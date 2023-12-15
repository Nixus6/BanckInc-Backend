package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;

import com.credibanco.bankincback.persistence.entity.TypeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public Card save(String cardId){

        Random numAleatorio = new Random();
        Long aleat1 = numAleatorio.nextLong(99999 - 10000 + 1) + 10000;
        Long aleat2 = numAleatorio.nextLong(99999 - 10000 + 1) + 10000;
        String idproducto = cardId + aleat1 + aleat2;
        // Obtén el LocalDateTime actual
        LocalDateTime fechaActual = LocalDateTime.now();
        // Suma tres años
        LocalDateTime fechaEnTresAnios = fechaActual.plusYears(3);
        Card card = Card.builder().cardId(Long.parseLong(idproducto)).balance(0).quota(0).typeCard(TypeCard.CREDIT).expiration(fechaEnTresAnios).build();
        return this.cardRepository.save(card);

    }

}
