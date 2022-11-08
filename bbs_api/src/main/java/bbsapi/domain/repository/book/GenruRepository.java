package bbsapi.domain.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import bbsapi.domain.model.Genru;

public interface GenruRepository extends JpaRepository<Genru, Long>{
	
}
