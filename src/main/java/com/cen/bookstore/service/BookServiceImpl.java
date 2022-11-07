package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.repo.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public String save(Book book) {
        repository.save(book);
        return "Added book with ISBN:" + book.getIsbn();
    }

    @Override
    public List<Book> getBookByISBN(long isbn) {
        return repository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getBookByAuthor(String author){
        return repository.findAllByAuthor(author);
    }



}
