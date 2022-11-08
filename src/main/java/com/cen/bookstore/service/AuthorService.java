package com.cen.bookstore.service;

import com.cen.bookstore.domain.Author;

import java.util.List;

public interface AuthorService {

    String save(Author author);

    //Service that get Book by isbn
    List<Author> getAuthorByName(String firstName);
    List<Author> getAuthorByLastName(String lastName);
}
