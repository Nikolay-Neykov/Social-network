package people.explorer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import people.explorer.entity.UserGroup;
import people.explorer.repository.UserGroupRepository;

@Transactional
@Service
public class UserGroupService {
	
	@Autowired
	private UserGroupRepository userGroupRepository;

	public void saveUserGroup(UserGroup userGroup) {
		userGroupRepository.save(userGroup);
	}
	
	public UserGroup getUserGroup(long id) {
		return userGroupRepository.findOne(id);
	}
	
	public Iterable<UserGroup> getUserGroupsByID(Iterable<Long> ids) {
		return userGroupRepository.findAll(ids);
	}
	
	public Iterable<UserGroup> getUserGroupsByCriteria(Specification<UserGroup> spec) {
		return userGroupRepository.findAll(spec);
	}
	
	public Iterable<UserGroup> getAllUserGroups() {
		return userGroupRepository.findAll();
	}
	
	public void deleteUserGroup(long id) {
		userGroupRepository.delete(id);
	}
}