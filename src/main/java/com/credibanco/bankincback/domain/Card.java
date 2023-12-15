package com.credibanco.bankincback.domain;

import com.credibanco.bankincback.persistence.entity.TypeCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Card {
    private long cardId;
    private int balance;
    private int quota;
    private boolean state;
    private TypeCard typeCard;
    private LocalDateTime expiration;
}
