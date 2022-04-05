package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ObtainById {
    public Mono<DTO> get(String id);
}
