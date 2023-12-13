package com.credibanco.bankincback.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaction", nullable = false)
    private Integer idTransaction;
    @Column(nullable = false)
    private Integer totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionState state;
    @Column(name = "card_id", nullable = false)
    private Integer cardId;
    @Column(name = "transactionAt", columnDefinition = "DATETIME")
    private LocalDateTime transactionDate;
    @Column(name = "createdAt", columnDefinition = "DATETIME")
    private LocalDateTime createDate;
    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id_card", insertable = false, updatable = false)
    private CardEntity card;
}
