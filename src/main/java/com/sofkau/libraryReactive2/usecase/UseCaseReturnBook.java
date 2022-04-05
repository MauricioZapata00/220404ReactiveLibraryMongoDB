package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.collections.Book;
import com.sofkau.libraryReactive2.dto.BookMapper;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseReturnBook implements BookAvailability {
    private final BookRepository repositorio;
    private final BookMapper mapperUtils;
    private final UseCaseModifyBook useCaseModifyBook;
    @Autowired
    public UseCaseReturnBook(BookMapper mapperUtils, BookRepository repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
        this.useCaseModifyBook = new UseCaseModifyBook(mapperUtils,repositorio);
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Book> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(recurso -> {
            if(recurso.isPrestado()){
                recurso.setPrestado(!recurso.isPrestado());
                return repositorio.save(recurso).thenReturn("Recurso devuelto satisfactoriamente");
            }
            return Mono.just("El recurso no se puede devolver porque no estaba prestado");
        });
    }
}
