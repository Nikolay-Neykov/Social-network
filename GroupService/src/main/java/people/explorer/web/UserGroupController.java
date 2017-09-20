package people.explorer.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import people.explorer.entity.UserGroup;
import people.explorer.service.UserGroupService;
import people.explorer.specification.UserGroupSpecificationBuilder;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);


	@RequestMapping(value = "/userGroups", method = RequestMethod.POST)
	public void createUserGroup(@RequestBody UserGroup userGroup) {
		logger.info("Create userGroup request received");

		try {
			userGroupService.saveUserGroup(userGroup);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process create userGroup request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/userGroups", method = RequestMethod.GET)
	public Iterable<UserGroup> getAllUserGroups(@RequestParam(value="search", required = false) String search) {
		try {
			if(search != null)
			{
				logger.info("Read userGroups with query request received");
				UserGroupSpecificationBuilder builder = new UserGroupSpecificationBuilder();
		        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)([a-zA-Z_0-9\\.\\$]+?),");
		        Matcher matcher = pattern.matcher(search + ",");
		        while (matcher.find()) {
					logger.info("key" + matcher.group(1) + "oper" + matcher.group(2) + "val" + matcher.group(3));
		            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		        }
		         
		        Specification<UserGroup> spec = builder.build();
				return userGroupService.getUserGroupsByCriteria(spec);
			} 
			else
			{
				logger.info("Read userGroups request received");
				return userGroupService.getAllUserGroups();
			}
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/userGroups/{ids}", method = RequestMethod.GET)
	public Iterable<UserGroup> getCategoriesByID(@PathVariable List<Long> ids) {
		try {
			logger.info("Read userGroups by ids request received");
			return userGroupService.getUserGroupsByID(ids);		
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/userGroups", method = RequestMethod.PUT)
	public void updateUserGroup(@RequestBody UserGroup userGroup) {
		logger.info("Update userGroup request received");

		try {
			userGroupService.saveUserGroup(userGroup);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process update userGroup request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/userGroups/{id}", method = RequestMethod.DELETE)
	public void deleteUserGroup(@PathVariable Integer id) {
		logger.info("Delete userGroup request received");

		try {
			userGroupService.deleteUserGroup(id);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process delete userGroup request", e);
			throw new RuntimeException(e);
		}
	}
}