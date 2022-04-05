package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.usecase.UseCaseRecommendedBooks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecommendedBookRouter {
    @Bean
    public RouterFunction<ServerResponse> recursosRecomendados(UseCaseRecommendedBooks useCaseRecursosRecomendados) {
        return route(
                GET("/library/recursosRecomendados/{clasificacion}/{area}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseRecursosRecomendados.get(request.pathVariable("clasificacion"),request.pathVariable("area")), DTO.class))
        );
    }
}
