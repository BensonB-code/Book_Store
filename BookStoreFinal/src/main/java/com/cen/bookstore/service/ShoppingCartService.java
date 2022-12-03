package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.domain.ShoppingCart;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ShoppingCartService {


    String save(ShoppingCart shoppingCart);

    String add(Book book);

    List<ShoppingCart> findAll();


    void delete(long isbn);



}
