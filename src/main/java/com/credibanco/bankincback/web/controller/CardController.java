package com.credibanco.bankincback.web.controller;

import com.credibanco.bankincback.domain.Card;
import com.credibanco.bankincback.domain.dto.RechargeBalanceDto;
import com.credibanco.bankincback.domain.dto.WebResponseDto;
import com.credibanco.bankincback.domain.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;
    private static final Logger log = LoggerFactory.getLogger(CardService.class);
    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }
    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("TODO BIEN");
    }

    @GetMapping("/{cardId}/number")
    @Operation(
            description = "Crear una nueva tarjeta",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Tarjeta Creada Con Exito!",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 201, \"status\" : \"Created\", \"message\" : \"Tarjeta Creada Con Exito!\" }"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request!",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 400, \"status\" : \"Bad Request!\", \"message\" : \"Bad Request!\" }"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error!",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"code\" : 500, \"status\" : \"Internal Server Error!\", \"message\" : \"Internal Server Error!\" }"
                                            )
                                    }
                            )
                    )
    }
    )
    public ResponseEntity<?> save(@PathVariable String cardId){
//            return new ResponseEntity<>(new WebResponseDto(HttpStatus.CREATED.value(),HttpStatus.CREATED.getReasonPhrase(),"Tarjeta Creada Con Exito!"),HttpStatus.OK);
            return new ResponseEntity<>(this.cardService.save(cardId), HttpStatus.CREATED);
    }
    @PostMapping("/enroll")
    public ResponseEntity<Card> activateCard(@RequestBody Card card){
            this.cardService.activateCard(card.getCardId());
            return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Card> blockCard(@PathVariable Long cardId){
        this.cardService.blockCard(cardId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/balance")
    public ResponseEntity<String> rechargeBalance(@RequestBody RechargeBalanceDto rechargeBalanceDto){
        try {
            Optional<List<Card>> cardsOptional = this.cardService.checkBalance(Long.parseLong(rechargeBalanceDto.getCardId()));
            if (cardsOptional.isPresent()) {
                Card card = cardsOptional.get().getFirst();
                this.cardService.rechargeBalance(card, Integer.parseInt(rechargeBalanceDto.getBalance()));
                return ResponseEntity.ok("Saldo recargado con éxito");
            } else {
                return ResponseEntity.badRequest().body("La tarjeta no existe");
            }
        } catch (NumberFormatException e) {
            log.error("Error al parsear números", e);
            return ResponseEntity.badRequest().body("Formato de número incorrecto");
        } catch (DataAccessException e) {
            log.error("Error de acceso a datos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<List<Card>> checkBalance(@PathVariable Long cardId){
        return this.cardService.checkBalance(cardId)
                .map(cards -> new ResponseEntity<>(cards, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
