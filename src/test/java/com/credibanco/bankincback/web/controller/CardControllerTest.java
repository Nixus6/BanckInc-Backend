package com.credibanco.bankincback.web.controller;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.service.CardService;
import com.credibanco.bankincback.persistence.entity.TypeCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void saveCard () throws Exception {

        given(cardService.save("123456")).willReturn(
//                (invocation)->invocation.getArgument(0)
                createSampleCard()
        );

        ResultActions response = mockMvc.perform(get("/card/123456/number")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSampleCard())));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardId",is(createSampleCard().getCardId())));

    }

    private Card createSampleCard() {
        return Card.builder()
                .cardId(123456)
                .balance(0)
                .quota(0)
                .typeCard(TypeCard.CREDIT)
                .expiration(LocalDateTime.now().plusYears(3))
                .state(false)
                .build();
    }
}