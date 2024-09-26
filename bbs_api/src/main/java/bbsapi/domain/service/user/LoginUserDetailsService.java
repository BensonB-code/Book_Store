package bbsapi.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bbsapi.domain.model.User;
import bbsapi.domain.repository.user.UserRepository;

@Service("borrowingUserDetailsService")
public class LoginUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
	       throws UsernameNotFoundException{
		User user = userRepository.findOneByUserId(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "is not found.");	
		}
		
		return new LoginUserDetails(user);
	}
	
}
