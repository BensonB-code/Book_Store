package bbsapi.app.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bbsapi.domain.model.Rating;
import bbsapi.domain.service.book.RatingService;

@RestController
@RequestMapping("api/books")
public class BooksController {
	@Autowired
	RatingService ratingService;
	
	//Get all books
	@RequestMapping(value="/all", method=RequestMethod.GET, produces = "application/json")
	List<Rating> getAllBooksList() {
		List<Rating> book = ratingService.findAll();
		return book;
	}
	
	//Get book by genru
	@RequestMapping(value="/genre/{genruId}", method=RequestMethod.GET, produces = "application/json")
	List<Rating> getBooksByGenruId(@PathVariable Long genruId) {
		List<Rating> book = ratingService.findByGenruId(genruId);
		return book;
	}
	
	//Get book by bookId
	@RequestMapping(method=RequestMethod.GET, produces = "application/json")
	Rating getOneBookById(@RequestParam Long book_id) {
		Rating book = ratingService.findOneByBookId(book_id);
	    return book;
	}

}
