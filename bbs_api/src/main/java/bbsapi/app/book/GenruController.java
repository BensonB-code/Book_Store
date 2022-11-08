package bbsapi.app.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bbsapi.domain.model.Genru;
import bbsapi.domain.service.book.GenruService;

@RestController
@RequestMapping("api/genre")
public class GenruController {
	@Autowired
	GenruService genruService;
	
	//Get all genre
	@RequestMapping(method=RequestMethod.GET, produces = "application/json")
	List<Genru>  getAllGenru() {
		List<Genru> genru = genruService.findAll();
		return genru;
	}
	
}
