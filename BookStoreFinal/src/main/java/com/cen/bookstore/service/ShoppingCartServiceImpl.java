package com.cen.bookstore.service;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.domain.ShoppingCart;
import com.cen.bookstore.repo.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShoppingCartServiceImpl extends Book implements ShoppingCartService {

     @Autowired
     private ShoppingCartRepository repository;

     @Override
     public String save(ShoppingCart shoppingCart) {
          repository.save(shoppingCart);
          return "Created Cart" + shoppingCart.getShoppingCart();
     }

     @Override
     public String add(Book book) {
          repository.insert(book);
          return "Book added to Cart" + book.getBookId();
     }

     @Override
     public List<ShoppingCart> findAll() {

          return repository.findAll();
     }
     @Override
     public void delete(long isbn) {
          repository.deleteBookByIsbn(isbn);
     }
    /* @Override
     public void delete(Book book) {
        repository.delete(book);

     }
*/

   /*  @Override
     public String delete(Book id) {
          repository.deleteById(id);
          return "Book deleted: " + book.getbookId();
     }

    */
}
