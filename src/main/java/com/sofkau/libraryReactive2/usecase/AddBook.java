package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddBook {
    public Mono<DTO> apply(DTO DTO);
}

