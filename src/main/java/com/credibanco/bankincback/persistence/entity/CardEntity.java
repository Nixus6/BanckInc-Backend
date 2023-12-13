package com.credibanco.bankincback.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="card")
@Getter
@Setter
@NoArgsConstructor
public class CardEntity {
    @Id
    @Column(name="id_card", nullable = false, length = 16)
    private Integer idCard;
    @Column(nullable = false)
    private Integer balance;
    @Column(nullable = false)
    private Integer quota;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCard typeCard;
    @Column(name = "expirationAt", columnDefinition = "DATETIME")
    private LocalDateTime expirationDate;
    @Column(name = "createdAt", columnDefinition = "DATETIME")
    private LocalDateTime createDate;
    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    private LocalDateTime updateDate;
    @OneToMany(mappedBy = "card")
    private List<TransactionEntity> transactions;

}
