package people.explorer.specification;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import people.explorer.entity.User;
import people.explorer.entity.UserGroup;

public class UserGroupSpecification implements Specification<UserGroup> {
		 
    private SearchCriteria criteria;
 
    public UserGroupSpecification(SearchCriteria searchCriteria) {
		this.criteria = searchCriteria;
	}

	@Override
    public Predicate toPredicate
      (Root<UserGroup> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
  
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
        	List<String> searchedUsers = Arrays.asList(criteria.getValue().toString().split("\\$"));
        	
        	query.distinct(true);
        	Join<UserGroup, User> join = root.join("users");
        	return join.get("id").in(searchedUsers);
        }
        return null;
    }
	
}
