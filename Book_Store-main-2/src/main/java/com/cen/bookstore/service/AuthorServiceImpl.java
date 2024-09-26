package com.cen.bookstore.service;


import com.cen.bookstore.domain.Author;
import com.cen.bookstore.repo.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;


    @Override
    public String save(Author author) {
        repository.save(author);
        return "Added author named: " + author.getFirstName();
    }

    @Override
    public List<Author> getAuthorByName(String firstname) {
        return repository.findAuthorByFirstName(firstname);
    }
}
