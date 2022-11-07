package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;

import java.util.List;

public interface BookService {

    String save(Book book);

    //Service that get Book by isbn
    List<Book> getBookByISBN(long isbn);

    //Gets all books with the same author
    List<Book> getBookByAuthor(String author);
}
