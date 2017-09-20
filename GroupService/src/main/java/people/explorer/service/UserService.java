package people.explorer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import people.explorer.entity.User;
import people.explorer.repository.UserRepository;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User getUser(String id) {
		return userRepository.findOne(id);
	}

	public Iterable<User> getUsersByID(Iterable<String> ids) {
		return userRepository.findAll(ids);
	}
	
	public Iterable<User> getUsersByCriteria(Specification<User> spec) {
		
		return userRepository.findAll(spec);
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void deleteUser(String id) {
		userRepository.delete(id);
	}
}
