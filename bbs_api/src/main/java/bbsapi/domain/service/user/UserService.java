package bbsapi.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bbsapi.domain.model.User;
import bbsapi.domain.repository.user.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean createUser(User user) throws Exception{
		if(user.getUserId()==null || user.getUserId()=="" ||
		   user.getPassword()==null || user.getPassword()=="" || 
		   user.getFirstName()==null || user.getFirstName()=="" || 
		   user.getLastName()==null || user.getLastName()=="" ) {
			throw new Exception("User info must not be null");
		}
		if(userRepository.findOneByUserId(user.getUserId())==null) {
			String pass = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(pass);
			userRepository.save(user);
			return true;
		}else {
			return false;  //The userId already exists.
		}
		
	}

}
