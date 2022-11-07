package com.cen.bookstore.api;

import com.cen.bookstore.domain.Book;
import com.cen.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    //Allows an admin to create a book
    //Post http://localhost:8080/book/admin/addBook
    @PostMapping("/admin/addBook")
    public String saveBook(@RequestBody Book book){
        return bookService.save(book);
    }
    //saves the book


    //Retrieves All Book by author
    //GET http://localhost:8080/book/<author>
    @GetMapping("/{author}")
    public List<Book> getBookByAuthor(@PathVariable("author") String author){
        return bookService.getBookByAuthor(author);
    }

    //Retrieves Book with input isbn
    //http://localhost:8080/book/findBook/<isbn>
    @GetMapping("/findBook/{isbn}")
    public List<Book> getBookByISBN(@PathVariable("isbn")long isbn){
        return bookService.getBookByISBN(isbn);
    }

}
