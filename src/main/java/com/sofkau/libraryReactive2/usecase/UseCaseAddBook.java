package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.dto.BookMapper;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseAddBook implements AddBook {
    private final BookRepository repositorio;
    private final BookMapper mapperUtils;
    //    @Autowired
    public UseCaseAddBook(BookMapper mapperUtils, BookRepository repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<DTO> apply(DTO DTO) {
        return repositorio.save(mapperUtils.mapperToDato(DTO.getId()).apply(DTO)).map(mapperUtils.mapDatoToDTO());
    }
}
