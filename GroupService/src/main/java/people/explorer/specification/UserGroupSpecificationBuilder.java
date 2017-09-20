package people.explorer.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import people.explorer.entity.UserGroup;

public class UserGroupSpecificationBuilder {
	     
    private final List<SearchCriteria> params;
 
    public UserGroupSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public UserGroupSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
    public Specification<UserGroup> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<UserGroupSpecification> specs = new ArrayList<UserGroupSpecification>();
        for (SearchCriteria param : params) {
            specs.add(new UserGroupSpecification(param));
        }
 
        Specification<UserGroup> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }	
}
