package bbsapi.app.review;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import bbsapi.domain.model.Book;
import bbsapi.domain.model.Rating;
import bbsapi.domain.model.Review;
import bbsapi.domain.model.ReviewForm;
import bbsapi.domain.model.ReviewId;
import bbsapi.domain.model.RoleName;
import bbsapi.domain.model.User;
import bbsapi.domain.service.book.BookService;
import bbsapi.domain.service.book.RatingService;
import bbsapi.domain.service.review.ReviewService;
import bbsapi.domain.service.user.LoginUserDetails;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	@Autowired
	RatingService ratingService;
	@Autowired 
	BookService bookService;
	
	//Get reviews by bookId
	@GetMapping
	List<Review> getReviewsById(@RequestParam Long book_id) {	
		List<Review> reviews = reviewService.findByBookId(book_id);	
	    return  reviews;	
	}
	
	//Delete reviews
	@DeleteMapping
	void deleteReview(@RequestBody ReviewId reviewId,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		//delete review
		reviewService.deleteReview(reviewId, userDetails.getUser());
		
        List<Review> reviews = reviewService.findByBookId(reviewId.getBookId());
        //get average rate
        Review aveReview= new Review();
        Double aveRate = aveReview.calAverage(reviews);
        //set rating
        Rating rating  = new Rating();
		rating.setId(reviewId.getBookId());
		rating.setAveRate(aveRate);
		ratingService.update(rating);
	}
	
	//Add reviews
	@PostMapping
	void createReview(
			@RequestBody ReviewForm reviewForm,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		Book book = bookService.findOneByBookId(reviewForm.getBookId());
		User user = userDetails.getUser();
		Long bookId = reviewForm.getBookId();
		String userId = user.getUserId();
		
		
		//add or edit review
		ReviewId reviewId = new ReviewId(bookId, userId);
		Review review = new Review();
		review.setReviewId(reviewId);
		review.setComment(reviewForm.getComment());
		review.setRate(reviewForm.getRate());
		review.setBook(book);
		review.setUser(user);
		reviewService.createReview(review);
		
		List<Review> reviews = reviewService.findByBookId(bookId);
		
        //get average rate
        Review aveReview= new Review();
        Double aveRate = aveReview.calAverage(reviews);
        //set rating
		Rating rating  = new Rating();
		rating.setId(bookId);
		rating.setAveRate(aveRate);
		rating.setBook(book);
		ratingService.update(rating);
		
	}
	
}
