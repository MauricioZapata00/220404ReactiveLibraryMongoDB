package com.sofkau.libraryReactive2.routers;

import com.sofkau.libraryReactive2.usecase.UseCaseEraseBook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EraseBookRouter {
    @Bean
    public RouterFunction<ServerResponse> borrarUnRecurso(UseCaseEraseBook useCaseEraseBook) {
        return route(
                DELETE("/library/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseEraseBook.get(request.pathVariable("id")), Void.class))
                        .onErrorResume((Error) -> ServerResponse.notFound().build())
        );
    }
}
