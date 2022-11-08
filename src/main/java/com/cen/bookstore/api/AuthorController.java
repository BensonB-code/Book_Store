package com.cen.bookstore.api;

import com.cen.bookstore.domain.Author;
import com.cen.bookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //Allows an admin to create a book
    //POST http://localhost:8080/author/admin/addAuthor
    @PostMapping("/admin/addAuthor")
    public String saveAuthor(@RequestBody Author author){
        return authorService.save(author);
    }


    //Allows retrieving Author by firstName
    //GET http://localhost:8080/author/findAuthor/<firstName>
    @GetMapping("/findAuthor/{firstName}")
    public List<Author> getAuthorByName(@PathVariable("firstName")String firstName){
        return authorService.getAuthorByName(firstName);
    }

    //GET http://localhost:8080/author/findAuthor/lastName/<lastName>
    @GetMapping("/findAuthor/lastName/{lastName}")
    public List<Author> getAuthorByLastName(@PathVariable("lastName")String lastName){
        return authorService.getAuthorByLastName(lastName);
    }

}
