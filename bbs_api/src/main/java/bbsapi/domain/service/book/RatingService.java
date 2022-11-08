package bbsapi.domain.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bbsapi.domain.model.Rating;
import bbsapi.domain.repository.book.RatingRepository;

@Service
@Transactional
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;
	
	public List<Rating> findAll() {
	  	   List<Rating> ratings = ratingRepository.findAllOrderBYBookIdAsc(); 
	  	   return ratings;
	   }
	   
	   public Rating findOneByBookId(Long bookId){
		   Rating rating = ratingRepository.findOneBybookId(bookId);
		   return rating;
	   }
	   
	   public List<Rating> findByGenruId(Long genruId){
		   List<Rating> rating = ratingRepository.findByGenruId(genruId);
		   return rating;
	   }
	   
       public Rating update(Rating rating) {
    	     ratingRepository.save(rating);
    	   return rating;
       }
}
