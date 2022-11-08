package bbsapi.domain.model;

public class ReviewForm {
	private Long bookId;
	
	private Integer rate;
	
	private String comment;


	public ReviewForm() {
	}

	public ReviewForm(Long bookId, Integer rate, String comment) {
		super();
		this.bookId = bookId;
		this.rate = rate;
		this.comment = comment;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	

}
