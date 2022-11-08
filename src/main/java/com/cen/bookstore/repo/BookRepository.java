package com.cen.bookstore.repo;

import com.cen.bookstore.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {


    //Access DB to find all book by input author
    List<Book> findAllByAuthor(String author);

    //Access DB to find book with input isbn
    List<Book> findByIsbn(long isbn);

    List<Book> findBygenre(String genre);



}
