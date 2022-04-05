package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.usecase.UseCaseAddBook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddBookRouter {
    @Bean
    public RouterFunction<ServerResponse> agregarUnRecurso(UseCaseAddBook useCaseAgregarUnRecurso ) {
        return route(POST("/library/agregarRecurso").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTO.class)
                        .flatMap(recursoDTO -> useCaseAgregarUnRecurso.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
