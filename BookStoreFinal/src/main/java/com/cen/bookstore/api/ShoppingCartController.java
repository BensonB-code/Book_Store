package com.cen.bookstore.api;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.domain.ShoppingCart;
import com.cen.bookstore.repo.ShoppingCartRepository;
import com.cen.bookstore.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;
    // CRUD operations for entire carts

    @Autowired
    private ShoppingCartRepository repository;


    // Post http://localhost:8080/cart/admin/createCart
    // {"userId: , shoppingCart:[]}
    @PostMapping("/admin/createCart")
    public String saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    @PostMapping("/addBookToCart")
    public String addToCart(@RequestBody Book book) {
        return shoppingCartService.add(book);
    }

    // http://localhost:8080/cart/allItems
    @GetMapping("/allItems")
    public List<ShoppingCart> getAllItemsInCart() {
        return shoppingCartService.findAll();
    }
    // http://localhost:8080/cart/deleteBook/
    @DeleteMapping("/deleteBook/{isbn}")
    public void delete(@PathVariable long isbn){
        shoppingCartService.delete(isbn);

    }

}
