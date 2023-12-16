package com.credibanco.bankincback.web.controller;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.Transaction;
import com.credibanco.bankincback.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase")
        public ResponseEntity<Transaction> purchaseTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(this.transactionService.purchaseTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable int transactionId){
        return this.transactionService.getTransaction(transactionId)
                .map(transactions -> new ResponseEntity<>(transactions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
