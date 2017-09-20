package people.explorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import people.explorer.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
