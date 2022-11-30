package bbsapi.app.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bbsapi.domain.model.ReviewForm;
import bbsapi.domain.model.RoleName;
import bbsapi.domain.model.User;
import bbsapi.domain.service.user.LoginUserDetails;
import bbsapi.domain.service.user.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	UserService userService;

	//Get Logined user info
	@RequestMapping(value="/auth", method=RequestMethod.GET, produces = "application/json")
	User getAuthUser(@AuthenticationPrincipal LoginUserDetails userDetails) {	
		User user= userDetails.getUser();
				return loginUserName(user.getUserId(), user.getFirstName(), user.getLastName(), user.getRoleName());
		}
	
	private User loginUserName(String userId, String firstName, String lastName, RoleName roleName) {
		User user = new User();
		user.setUserId(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRoleName(roleName);
		return user;	
	}
	
	//Create new user
	@RequestMapping(value="/create", method=RequestMethod.POST)
	boolean createUser(@RequestBody User user) throws Exception {
		User newUser = new User();
		newUser.setUserId(user.getUserId());
		newUser.setPassword(user.getPassword());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setRoleName(RoleName.USER);
		
		//If new user is registerd successfully, return 'true'. If not, 'false'. 
		return userService.createUser(newUser);
	}
	
		
}



