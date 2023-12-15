package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;

import com.credibanco.bankincback.persistence.entity.TypeCard;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import org.slf4j.Logger;


@Service
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);
    private final CardRepository cardRepository;


    private static final boolean BLOQUED = false;
    private static final boolean UNLOCKED = true;

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
        Card card = Card.builder().cardId(Long.parseLong(idproducto)).balance(0).quota(0).typeCard(TypeCard.CREDIT).expiration(fechaEnTresAnios).state(BLOQUED).build();
        return this.cardRepository.save(card);

    }
    @Transactional
    public void activateCard(Long cardId){
        log.info("entro svice");
        this.cardRepository.activateCard(cardId);
    }

}
