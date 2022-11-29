package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    String save(Book book);

    //Service that get Book by isbn
    List<Book> getBookByISBN(long isbn);

    //Gets all books with the same author
    List<Book> getBookByAuthor(String author);

    List<Book> getBookByGenre(String genre);

    List<Book> getBookWithSorting(String field);

    Page<Book> getBookPage(org.springframework.data.domain.Pageable p);

    List<Book> getByRating(Integer minRating, Integer maxRating);
}
