package bbsapi.domain.repository.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import bbsapi.domain.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	@Query("SELECT DISTINCT x FROM Rating x LEFT JOIN FETCH x.book b LEFT JOIN FETCH b.genru g ORDER BY x.id ASC")
	List<Rating> findAllOrderBYBookIdAsc();
	
	@Query("SELECT x FROM Rating x LEFT JOIN FETCH x.book b LEFT JOIN FETCH b.genru g WHERE x.id = :bookId")
	Rating findOneBybookId(@Param("bookId") Long bookId);
	
	@Query("SELECT x FROM Rating x LEFT JOIN FETCH x.book b LEFT JOIN FETCH b.genru g WHERE g.id = :genruId")
	List<Rating> findByGenruId(@Param("genruId") Long genruId);
}
