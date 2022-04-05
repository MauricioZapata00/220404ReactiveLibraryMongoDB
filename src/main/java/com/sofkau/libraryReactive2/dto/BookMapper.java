package com.sofkau.libraryReactive2.dto;

import com.sofkau.libraryReactive2.collections.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Component
public class BookMapper {

    public Book fromDTO(DTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitulo(dto.getTitulo());
        book.setClasificacion(dto.getClasificacion());
        book.setArea(dto.getArea());
        book.setPrestado(dto.isPrestado());
        book.setFechaPrestamo(dto.getFechaPrestamo());
        return book;
    }

    public DTO fromCollection(Book collection) {
        DTO DTO = new DTO();
        DTO.setId(collection.getId());
        DTO.setTitulo(collection.getTitulo());
        DTO.setClasificacion(collection.getClasificacion());
        DTO.setArea(collection.getArea());
        DTO.setPrestado(collection.isPrestado());
        DTO.setFechaPrestamo(collection.getFechaPrestamo());
        return DTO;
    }

    public List<DTO> fromCollectionList(List<Book> collection) {
        if (collection == null) {
            return null;
        }
        List<DTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Book book = (Book) listTracks.next();
            list.add(fromCollection(book));
        }

        return list;
    }

    public Function<DTO, Book> mapperToDato(String id) {
        return updateDato -> {
            var recurso = new Book();
            recurso.setId(id);
            recurso.setTitulo(updateDato.getTitulo());
            recurso.setClasificacion(updateDato.getClasificacion());
            recurso.setArea(updateDato.getArea());
            recurso.setPrestado(updateDato.isPrestado());
            recurso.setFechaPrestamo(updateDato.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Book, DTO> mapDatoToDTO() {
        return entity -> new DTO(entity.getId(), entity.getTitulo(), entity.getClasificacion(), entity.getArea(), entity.isPrestado());
    }
}
