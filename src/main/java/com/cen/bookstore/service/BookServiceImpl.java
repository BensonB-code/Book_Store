package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.repo.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<Book> getBookByGenre(String genre) {
        return repository.findBygenre(genre);
    }

    @Override
    public List<Book> getBookWithSorting(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Book> getBookPage(Pageable p) {
        return repository.findAll(p);
    }



}
