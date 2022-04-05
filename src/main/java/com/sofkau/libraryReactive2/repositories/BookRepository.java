package com.sofkau.libraryReactive2.repositories;

import com.sofkau.libraryReactive2.collections.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}

