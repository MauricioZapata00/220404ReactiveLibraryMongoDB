package com.sofkau.libraryReactive2.usecase;

import com.sofkau.libraryReactive2.dto.DTO;
import com.sofkau.libraryReactive2.dto.BookMapper;
import com.sofkau.libraryReactive2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseRecommendedBooks implements RecommendedBooks {
    private final BookRepository repositorio;
    private final BookMapper mapperUtils;
    @Autowired
    public UseCaseRecommendedBooks(BookMapper mapperUtils, BookRepository repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<DTO> get(String clasificacion, String area) {
        Flux<DTO> listaRecursos = repositorio.findAll().map(mapperUtils.mapDatoToDTO());
        if (!clasificacion.equalsIgnoreCase("none") && area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getClasificacion().equalsIgnoreCase(clasificacion));
        }if (clasificacion.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getArea().equalsIgnoreCase(area));
        }if (!clasificacion.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none")){
            return listaRecursos.filter(recurso -> recurso.getArea().equalsIgnoreCase(area))
                    .filter(recurso -> recurso.getClasificacion().equalsIgnoreCase(clasificacion));
        }
        return listaRecursos;
    }
}
