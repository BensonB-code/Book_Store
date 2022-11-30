package bbsapi.domain.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bbsapi.domain.model.Review;
import bbsapi.domain.model.ReviewId;

public interface ReviewRepository extends JpaRepository<Review, ReviewId>{
	@Query("SELECT x FROM Review x LEFT JOIN FETCH x.book b LEFT JOIN FETCH x.user u WHERE b.bookId = :bookId")
	List<Review> findByBookIdOrderByRegistrationDate(@Param("bookId") Long bookId);
	
	@Query("SELECT x FROM Review x LEFT JOIN FETCH x.book b LEFT JOIN FETCH x.user u WHERE b.bookId = :bookId and u.userId = :userId")
	Review findOneByBookIdAndUserId(@Param("bookId") Long bookId, @Param("userId") String userId);


}
