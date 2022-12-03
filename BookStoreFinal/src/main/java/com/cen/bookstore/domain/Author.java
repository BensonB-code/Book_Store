package com.cen.bookstore.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Document
public class Author {

    @Id
    private String authorId;
    private String firstName;
    private String lastName;
    private String biography;
    private String publisher;
    private Collection<Book> books = new ArrayList<>();

    public Author(String firstName, String lastName, String biography, String publisher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publisher = publisher;
    }

    public void authorDisplay(){

        System.out.println("FirstName:" + firstName);
        System.out.println("LastName:" + lastName);
        System.out.println("Biography:" + biography);
        System.out.println("Publisher" + publisher);
    }
}
