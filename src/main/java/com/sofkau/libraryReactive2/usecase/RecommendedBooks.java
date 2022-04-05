package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RecommendedBooks {
    public Flux<DTO> get(String clasificacion, String area);
}