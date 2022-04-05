package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.dto.BookMapper;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class UseCaseObtainBooks implements Supplier<Flux<DTO>> {
    private final BookRepository repositorio;
    private final BookMapper mapperUtils;
    @Autowired
    public UseCaseObtainBooks(BookMapper mapperUtils, BookRepository repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<DTO> get() {
        return repositorio.findAll().map(mapperUtils.mapDatoToDTO());
    }
}
