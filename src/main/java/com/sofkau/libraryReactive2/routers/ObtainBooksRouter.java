package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ObtainBooksRouter {
    @Bean
    public RouterFunction<ServerResponse> obtenerTodos(UseCaseObtainBooks useCaseObtainBooks) {
        return route(
                GET("/library/obtenerTodos").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseObtainBooks.get(), DTO.class))
        );
    }
}