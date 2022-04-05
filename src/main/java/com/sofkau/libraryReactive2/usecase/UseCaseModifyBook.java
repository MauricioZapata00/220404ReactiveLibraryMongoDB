package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.dto.BookMapper;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseModifyBook implements AddBook {
    private final BookRepository repositorio;
    private final BookMapper mapperUtils;
    @Autowired
    public UseCaseModifyBook(BookMapper mapperUtils, BookRepository repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<DTO> apply(DTO DTO) {
        String id = DTO.getId();
        if (id == null){
            new RuntimeException("El id es requerido");
        }
        return repositorio.save(mapperUtils.mapperToDato(DTO.getId()).apply(DTO)).map(mapperUtils.mapDatoToDTO());
    }
}
