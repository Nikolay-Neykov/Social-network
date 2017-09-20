package people.explorer.specification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import people.explorer.entity.Category;
import people.explorer.entity.Location;

public class LocationSpecification implements Specification<Location> {
		 
    private SearchCriteria criteria;
 
    public LocationSpecification(SearchCriteria searchCriteria) {
		this.criteria = searchCriteria;
	}

	@Override
    public Predicate toPredicate
      (Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
  
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if(criteria.getOperation().equalsIgnoreCase("~")){
        	List<String> searchedCategories = Arrays.asList(criteria.getValue().toString().split("\\$"));
        	List<Long> searchedCategoriesIds = searchedCategories.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
        	
        	//query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        	query.distinct(true);
        	Join<Location, Category> join = root.join("categories");
        	return join.in(searchedCategoriesIds);
        }
        return null;
    }
	
}
