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
import org.springframework.web.bind.annotation.RestController;

import people.explorer.entity.Category;
import people.explorer.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public void createCategory(@RequestBody Category category) {
		logger.info("Create category request received");

		try {
			categoryService.saveCategory(category);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process create category request", e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public Iterable<Category> getAllCategories() {
		try {
			logger.info("Read categories request received");
			return categoryService.getAllCategories();					
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/categories/{ids}", method = RequestMethod.GET)
	public Iterable<Category> getCategoriesByID(@PathVariable List<Long> ids) {
		try {
			logger.info("Read categories by ids request received");
			return categoryService.getCategoriesByID(ids);		
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/categories", method = RequestMethod.PUT)
	public void updateCategory(@RequestBody Category category) {
		logger.info("Update category request received");

		try {
			categoryService.saveCategory(category);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process update category request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable Integer id) {
		logger.info("Delete category request received");

		try {
			categoryService.deleteCategory(id);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process delete category request", e);
			throw new RuntimeException(e);
		}
	}
}
