package bbsapi.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Review implements Serializable{
	@EmbeddedId
	private ReviewId reviewId;
	
	private Integer rate;
	
	@Lob
	private String comment;
	
	
	@ManyToOne
	@JoinColumn(name="book_id", insertable=false, updatable=false)
	@MapsId("bookId")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	@MapsId("userId")
	private User user;
	
	// Constructor	
	public Review() {}


	public Review(ReviewId reviewId, Integer rate, String comment, Book book, User user) {
		this.reviewId = reviewId;
		this.rate = rate;
		this.comment = comment;
		this.book = book;
		this.user = user;
	}

	//Getter and Setter
    public ReviewId getReviewId() {
		return reviewId;
	}


	public void setReviewId(ReviewId reviewId) {
		this.reviewId = reviewId;
	}
	

	public Integer getRate() {
		return rate;
	}


	public void setRate(Integer rate) {
		this.rate = rate;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	//calculate average Rating by bookId
	public Double calAverage(List<Review> target) {
		//number of review record
		Long numRate = target
				.stream()
				.count();
		if(numRate==0) {
			return 0.0;
		}
        //total rating
	    Long sumRate = target
				.stream()
				.mapToLong(x -> x.getRate() )
				.sum();
        //avarage rating
	    Double aveRate = ((double)sumRate / (double)numRate);	
	    //To display the numbers truncating the numbers beyond the second decimal point.
	    BigDecimal x = new BigDecimal(aveRate);
	    x = x.setScale(1, BigDecimal.ROUND_FLOOR);
	    Double rate = x.doubleValue();
			
		return rate;
	}
	
	
	
}  
