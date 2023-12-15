package com.credibanco.bankincback.web.controller;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }


    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("TODO BIEN");
    }

    @GetMapping("/{cardId}/number")
    public ResponseEntity<Card> save(@PathVariable String cardId){
            return new ResponseEntity<>(cardService.save(cardId), HttpStatus.CREATED);
    }
}
