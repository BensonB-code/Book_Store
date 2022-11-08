package com.cen.bookstore.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book {

    @Id
    private String bookId;
    private long isbn;
    private String title;
    private double rating;
    private String genre;
    private String author;
    private String description;
    private double price;
    private String publisher;
    private int year;
    private boolean sold;

    private int copiesSold;



        public Book(long isbn,
                String title,
                double rating,
                String genre,
                String author,
                String description,
                double price,
                String publisher,
                int year,
                boolean sold,
                    int copiesSold) {

        this.isbn = isbn;
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.author = author;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.year = year;
        this.sold = sold;
        this.copiesSold = copiesSold;


    }
}
