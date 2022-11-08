package bbsapi.domain.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Book implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookId;
	
	private String title;
	
	@Lob
	private String text;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="genru_id")
	private Genru genru;
	
	private String author;
	
	private String releasedDate;
	
	private String image;
	
	private Integer price;
	
	

    // Constructor	
	public Book() {}


    public Book(Long bookId, String title, String text, Genru genru, String author, String releasedDate,
			String image, Integer price) {
		this.bookId = bookId;
		this.title = title;
		this.text = text;
		this.genru = genru;
		this.author = author;
		this.releasedDate = releasedDate;
		this.image = image;
		this.price = price;
	}


	//Getter and Setter
	public Long getBookId() {
		return bookId;
	}


	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Genru getGenru() {
		return genru;
	}


	public void setGenru(Genru genru) {
		this.genru = genru;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getReleasedDate() {
		return releasedDate;
	}


	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}

	
	
}
