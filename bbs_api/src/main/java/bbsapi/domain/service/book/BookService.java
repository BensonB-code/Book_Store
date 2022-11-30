package bbsapi.domain.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bbsapi.domain.model.Book;
import bbsapi.domain.repository.book.BookRepository;

@Service
@Transactional
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public Book findOneByBookId(Long bookId){
		   Book book = bookRepository.findOneBybookId(bookId);
		   return book;
	   }
}
