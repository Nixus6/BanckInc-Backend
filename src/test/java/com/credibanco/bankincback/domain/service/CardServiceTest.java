package com.credibanco.bankincback.domain.service;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.repository.CardRepository;
import com.credibanco.bankincback.persistence.entity.CardEntity;
import com.credibanco.bankincback.persistence.entity.TypeCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    public static final boolean BLOQUED = false;

    private Card card;

    @BeforeEach
    void setup(){
        card = Card.builder()
                .cardId(123456L)
                .balance(0)
                .quota(0)
                .typeCard(TypeCard.DEBIT)
                .expiration(LocalDateTime.now().plusYears(3))
                .state(BLOQUED)
                .build();
    }

    @Test
    @DisplayName("A partir del identificador de producto, generar el número de tarjeta.")
    void saveCard (){
        given(cardRepository.save(any(Card.class))).willReturn(card);

        //when
        Card saveCard = cardService.save(String.valueOf(card.getCardId()));

        assertThat(saveCard).isNotNull();
    }

    @Test
    @DisplayName("Validar que los 6 primeros dígitos corresponden al id del producto")
    void when_create_new_product_then_generate_id_with_id_product(){
        given(cardRepository.save(any(Card.class))).willReturn(card);

        //when
        Card saveCard = cardService.save(String.valueOf(card.getCardId()));
        String cadenaNumero = Long.toString(saveCard.getCardId());
        // Tomar los primeros 6 caracteres
        String primerosSeisDigitos = cadenaNumero.substring(0, Math.min(cadenaNumero.length(), 6));

        assertEquals(Long.toString(card.getCardId()),cadenaNumero);
    }

    @Test
    @DisplayName("Cuando ingrese un nuevo producto entonces fecha creacion mayor a tres años")
    void when_create_then_date__expiration_more_three_years(){
        //given
        given(cardRepository.save(any(Card.class))).willReturn(card);

        //when
        Card saveCard = cardService.save(String.valueOf(card.getCardId()));
        LocalDateTime currentDatePlus = LocalDateTime.now().plusYears(3);

        //then
        //Dia
        assertThat(currentDatePlus.getDayOfMonth()).isEqualTo( saveCard.getExpiration().getDayOfMonth());
        //Mes
        assertThat(currentDatePlus.getMonth()).isEqualTo( saveCard.getExpiration().getMonth());
        //Año
        assertThat(currentDatePlus.getYear()).isEqualTo(saveCard.getExpiration().getYear());
    }
}