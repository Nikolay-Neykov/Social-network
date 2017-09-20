package people.explorer.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import people.explorer.entity.User;
import people.explorer.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public void createUser(@RequestBody User user) {
		logger.info("Create user request received");

		try {
			userService.saveUser(user);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process create user request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Iterable<User> getAllUsers(@RequestParam(value="search", required = false) String search) {
		try {
//			if(search != null)
//			{
//				logger.info("Read users with query request received");
//				SpecificationBuilder builder = new SpecificationBuilder();
//		        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)([a-zA-Z_0-9\\.\\$]+?),");
//		        Matcher matcher = pattern.matcher(search + ",");
//		        while (matcher.find()) {
//					logger.info("key" + matcher.group(1) + "oper" + matcher.group(2) + "val" + matcher.group(3));
//		            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
//		        }
//		         
//		        Specification<User> spec = builder.build();
//				return userService.getUsersByCriteria(spec);
//			} 
//			else
//			{
				logger.info("Read users request received");
				return userService.getAllUsers();
//			}		
		} catch (Exception e) {
			logger.info("Error occurred while trying to process read users request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/users/{ids}", method = RequestMethod.GET)
	public Iterable<User> getUsersByID(@PathVariable List<String> ids) {
		try {
			logger.info("Read users by ids request received");
			return userService.getUsersByID(ids);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		logger.info("Update user request received");

		try {
			userService.saveUser(user);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process update user request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String id) {
		logger.info("Delete user request received");

		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process delete user request", e);
			throw new RuntimeException(e);
		}
	}
}
