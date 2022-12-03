package com.cen.bookstore.repo;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.domain.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    Book insert(Book book);
    List<ShoppingCart> findAll();
    Book deleteBookByIsbn(long isbn);
    // void addNewBook(Book book);
}
