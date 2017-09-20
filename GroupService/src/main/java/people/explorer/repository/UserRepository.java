package people.explorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import people.explorer.entity.User;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User>{
	
}