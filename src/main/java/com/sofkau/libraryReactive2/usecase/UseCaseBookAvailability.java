package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Book;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseBookAvailability implements BookAvailability {
    private final BookRepository repositorio;
    @Autowired
    public UseCaseBookAvailability(BookRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Book> recursoMono = repositorio.findById(id);
        return recursoMono.map(recurso -> recurso.isPrestado() == true ?
                "Recurso no disponible, prestado el día: "+ recurso.getFechaPrestamo() : "Recurso disponible" );
    }
}
