package com.credibanco.bankincback.persistence.entity;

import com.credibanco.bankincback.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="card")
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
public class CardEntity extends AuditableEntity {
    @Id
    @Column(name="id_card", nullable = false, length = 16)
    private Long idCard;
    @Column(nullable = false)
    private Integer balance;
    @Column(nullable = false)
    private Integer quota;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCard typeCard;
    @Column(name = "expirationAt", columnDefinition = "DATETIME")
    private LocalDateTime expirationDate;
    @OneToMany(mappedBy = "card")
    private List<TransactionEntity> transactions;
}
