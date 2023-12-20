package com.credibanco.bankincback.web.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig  {


    @Bean
    public OpenAPI baseOpenApi() {
//        ApiResponse badRequestApi = new ApiResponse().content(
//                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
//                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
//                                new Example().value("{\"code\" : 400, \"status\" : \"Bad Request!\", \"message\" : \"Bad Request!\" }")))
//        );

        return new OpenAPI().info(new Info().title("Spring Doc").version("1.0.0").description("Spring doc"));
    }


}
