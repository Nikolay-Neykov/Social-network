package people.explorer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import people.explorer.entity.Category;
import people.explorer.repository.CategoryRepository;

@Transactional
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public Category getCategory(long id) {
		return categoryRepository.findOne(id);
	}
	
	public Iterable<Category> getCategoriesByID(Iterable<Long> ids) {
		return categoryRepository.findAll(ids);
	}
	
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public void deleteCategory(long id) {
		categoryRepository.delete(id);
	}
}
