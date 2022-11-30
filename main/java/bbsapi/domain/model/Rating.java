package bbsapi.domain.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class Rating implements Serializable{
	@Id
	private Long id;   //(= Book.bookId)
	
	private Double aveRate;
	
	@OneToOne
	@MapsId("id")
	@JoinColumn(name="book_id")
	private Book book;
	
	
	//Constructor
	public Rating() {}	

	public Rating(Long id, Double aveRate, Book book) {
		this.id = id;
		this.aveRate = aveRate;
		this.book = book;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAveRate() {
		return aveRate;
	}

	public void setAveRate(Double aveRate) {
		this.aveRate = aveRate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	
	
	
	
}
