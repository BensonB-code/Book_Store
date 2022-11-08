package bbsapi.domain.service.review;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bbsapi.domain.model.Review;
import bbsapi.domain.model.ReviewId;
import bbsapi.domain.model.RoleName;
import bbsapi.domain.model.User;
import bbsapi.domain.repository.review.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	
	public List<Review> findByBookId(Long bookId) {
	  	   List<Review> reviews = reviewRepository.findByBookIdOrderByRegistrationDate(bookId);
	  	   return reviews;
	   }
	
	public Review findReview(Long bookId, String userId) {
	       Review review= reviewRepository.findOneByBookIdAndUserId(bookId, userId);
	       return review;
}
	
	public void deleteReview(ReviewId reviewId, User user) {

		//ADMIN can delete all reviews, but USER can delete their own review.
		if(RoleName.ADMIN != user.getRoleName() && 
				!Objects.equals(reviewId.getUserId(), user.getUserId())) {
			throw new AccessDeniedException("Unauthenticated");
		}
		
		reviewRepository.deleteById(reviewId);		
	}
	
	public Review createReview(Review review){
        reviewRepository.save(review);
       
		return review;
	}
	
    
}
