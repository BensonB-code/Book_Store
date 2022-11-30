package bbsapi.domain.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bbsapi.domain.model.Genru;
import bbsapi.domain.repository.book.GenruRepository;

@Service
@Transactional
public class GenruService {
	
	@Autowired
	GenruRepository genruRepository;
	
	public List<Genru> findAll(){
		List<Genru> genru= genruRepository.findAll();
		   return genru;
	   }

}
