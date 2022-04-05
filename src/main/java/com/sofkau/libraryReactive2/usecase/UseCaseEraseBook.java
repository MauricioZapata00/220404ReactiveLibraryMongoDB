package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UseCaseEraseBook implements EraseBook {
    private final BookRepository repositorio;
    @Autowired
    public UseCaseEraseBook(BookRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Mono get(String id) {
        if (Objects.isNull(id)) {
            return Mono.empty();
        }
        return repositorio.deleteById(id);
    }
}
