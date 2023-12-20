package com.credibanco.bankincback.web.controller;

import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.domain.service.CardService;
import com.credibanco.bankincback.domain.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(CardService.class);
    private final TransactionService transactionService;
    private final CardService cardService;
    @Autowired
    public TransactionController(TransactionService transactionService, CardService cardService){
        this.transactionService = transactionService;
        this.cardService = cardService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Transaction> purchaseTransaction(@RequestBody Transaction transaction){
        return this.cardService.checkBalance(transaction.getCardId())
                .map(cards -> new ResponseEntity<>(this.transactionService.purchaseTransaction(cards.getFirst(),transaction),HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable int transactionId){
        return this.transactionService.getTransaction(transactionId)
                .map(transactions -> new ResponseEntity<>(transactions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/anulation")
    public ResponseEntity<Transaction> anulationTransaction(@RequestBody Transaction transaction){
        this.transactionService.getTransaction(transaction.getTransactionId())
                .ifPresent(transactions -> {
                    this.cardService.checkBalance(transactions.getFirst().getCardId())
                            .ifPresent(cards -> {
                                this.transactionService.anulationTransaction(transactions.getFirst(),cards.getFirst());
                            });
                });
        return ResponseEntity.ok().build();
    }
}
