package com.cen.bookstore.repo;

import com.cen.bookstore.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    //Finds all authors by their first name
    List<Author> findAuthorByFirstName(String firstName);

    List<Author> findAuthorByLastName(String lastName);
}
