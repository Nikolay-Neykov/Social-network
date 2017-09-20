package people.explorer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import people.explorer.entity.Location;
import people.explorer.repository.LocationRepository;

@Transactional
@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	public void saveLocation(Location location) {
		locationRepository.save(location);
	}
	
	public Location getLocation(long id) {
		return locationRepository.findOne(id);
	}

	public Iterable<Location> getLocationsByID(Iterable<Long> ids) {
		return locationRepository.findAll(ids);
	}
	
	public Iterable<Location> getLocationsByCriteria(Specification<Location> spec) {
		
		return locationRepository.findAll(spec);
	}

	public Iterable<Location> getAllLocations() {
		return locationRepository.findAll();
	}
	
	public void deleteLocation(long id) {
		locationRepository.delete(id);
	}
}
