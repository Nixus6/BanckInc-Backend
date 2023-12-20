package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;

import com.credibanco.bankincback.persistence.entity.TypeCard;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        try {
            Random numAleatorio = new Random();
            long aleat1 = numAleatorio.nextLong(99999 - 10000 + 1) + 10000;
            long aleat2 = numAleatorio.nextLong(99999 - 10000 + 1) + 10000;
            String idproducto = cardId + aleat1 + aleat2;
            // Obtén el LocalDateTime actual
            LocalDateTime fechaActual = LocalDateTime.now();
            // Suma tres años
            LocalDateTime fechaEnTresAnios = fechaActual.plusYears(3);
            Card card = Card.builder().cardId(Long.parseLong(idproducto)).balance(0).quota(0).typeCard(TypeCard.CREDIT).expiration(fechaEnTresAnios).state(BLOQUED).build();
            return this.cardRepository.save(card);
        }catch (DataAccessException e) {
            // Manejar excepciones específicas de acceso a datos (por ejemplo, problemas de base de datos)
            log.error("Error de acceso a datos al guardar la tarjeta", e);
            throw new DataAccessResourceFailureException("Error al guardar la tarjeta", e);
        } catch (Exception e) {
            // Manejar otras excepciones genéricas que puedan ocurrir
            log.error("Error al guardar la tarjeta", e);
            throw new RuntimeException("Error al guardar la tarjeta", e);
        }

    }
    @Transactional
    public void activateCard(Long cardId){
        log.info("entro svice");
        this.cardRepository.activateCard(cardId);
    }

    @Transactional
    public void blockCard(Long cardId){
        this.cardRepository.blockCard(cardId);
    }
    @Transactional
    public void rechargeBalance(Card card, int requestBalance){
        int newBalance = card.getBalance() + requestBalance;
        this.cardRepository.rechargeBalance(newBalance, card.getCardId());
    }

    public Optional<List<Card>> checkBalance(Long cardId) {
        return this.cardRepository.checkBalance(cardId);
    }
}
