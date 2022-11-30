package bbsapi.domain.repository.book;



import bbsapi.domain.service.AuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepositoryRepository<Author, String> {

    //Finds all authors by their first name
    List<Author> findAuthorByFirstName(String firstName);
    List<Author> findAuthorByLastName(String lastName);
}
