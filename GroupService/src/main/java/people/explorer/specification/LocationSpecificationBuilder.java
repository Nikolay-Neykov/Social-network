package people.explorer.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import people.explorer.entity.Location;

public class LocationSpecificationBuilder {
	     
    private final List<SearchCriteria> params;
 
    public LocationSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public LocationSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
    public Specification<Location> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<LocationSpecification> specs = new ArrayList<LocationSpecification>();
        for (SearchCriteria param : params) {
            specs.add(new LocationSpecification(param));
        }
 
        Specification<Location> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }	
}
