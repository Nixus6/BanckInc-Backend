package com.credibanco.bankincback.domain;

import com.credibanco.bankincback.persistence.entity.TransactionState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Transaction {
    private int transactionId;
    private int totalPrice;
    private TransactionState state;
    private Long cardId;
    private LocalDateTime transactionDate;
}
