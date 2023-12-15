package com.credibanco.bankincback.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private int transactionId;
    private int totalPrice;
    private String state;
    private int cardId;
    private LocalDateTime transactionDate;
}
