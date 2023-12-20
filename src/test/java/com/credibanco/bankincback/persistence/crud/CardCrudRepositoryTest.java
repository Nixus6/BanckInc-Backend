package com.credibanco.bankincback.persistence.crud;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.entity.TypeCard;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



//@DataJdbcTest

//@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CardCrudRepositoryTest {

    @Autowired
    private CardCrudRepository cardCrudRepository;

    private CardEntity cardEntity;

    @BeforeEach
    void setup(){
        cardEntity = CardEntity.builder()
                .idCard(1234561234567894L)
                .balance(0)
                .quota(0)
                .typeCard(TypeCard.CREDIT)
                .expirationDate(LocalDateTime.now().plusYears(3))
                .state(false)
                .build();
    }

    @DisplayName("Test para guardar una tarjeta")
    @Test
    void testSaveCard(){
        //given - dado o condición previa o configuración

        //when - acción o el comportamiento que vamos a probar
        CardEntity cardSave = cardCrudRepository.save(cardEntity);

        //then - verificar la salida
        assertThat(cardSave).isNotNull();
        assertThat(cardSave.getIdCard()).isGreaterThan(0);
        assertThat(cardSave.getState()).isFalse();
    }

    @DisplayName("Test para activar la tarjeta")
    @Test
    void testActivateCard(){
        // Dado
        cardCrudRepository.save(cardEntity);

        // Cuando
        CardEntity cardToActivate = cardCrudRepository.findById(cardEntity.getIdCard()).get();
        System.out.println("Estado antes de la activación: " + cardToActivate.getIdCard());
        cardCrudRepository.activateCard(cardToActivate.getIdCard());

        // Then
        CardEntity activatedCard = cardCrudRepository.findById(cardToActivate.getIdCard()).orElse(null);
        System.out.println("Estado después de la activación: " + cardToActivate.getIdCard());

        assertThat(activatedCard).isNotNull(); // Verificar que la tarjeta fue encontrada
        assertThat(activatedCard.getState()).isTrue(); // Verificar que la tarjeta está activada
    }
    @DisplayName("Test para bloquear la tarjeta")
    @Test
    void blockCard(){
        cardEntity.setState(true);
        cardCrudRepository.save(cardEntity);

        cardCrudRepository.blockCard(cardEntity.getIdCard());

        CardEntity blockCard = cardCrudRepository.findById(cardEntity.getIdCard()).orElse(null);

        assertThat(blockCard).isNotNull();
        assertThat(blockCard.getState()).isFalse();
    }

    @DisplayName("Test para consultar saldo por id")
    @Test
    void testCheckBalance(){
        cardCrudRepository.save(cardEntity);

        //when
        List<CardEntity> card = cardCrudRepository.findByIdCard(cardEntity.getIdCard());

        assertThat(card.getFirst()).isNotNull();
    }

}
