package com.credibanco.bankincback.persistence.entity;

import com.credibanco.bankincback.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity extends AuditableEntity {
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
    private Long cardId;
    @Column(name = "transactionAt", columnDefinition = "DATETIME")
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id_card", insertable = false, updatable = false)
    private CardEntity card;
}
