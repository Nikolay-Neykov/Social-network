package people.explorer.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import people.explorer.entity.Location;
import people.explorer.service.LocationService;
import people.explorer.specification.LocationSpecificationBuilder;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class LocationController {

	@Autowired
	private LocationService locationService;

	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	public void createLocation(@RequestBody Location location) {
		logger.info("Create location request received");

		try {
			locationService.saveLocation(location);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process create location request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public Iterable<Location> getAllLocations(@RequestParam(value="search", required = false) String search) {
		try {
			if(search != null)
			{
				logger.info("Read locations with query request received");
				LocationSpecificationBuilder builder = new LocationSpecificationBuilder();
		        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)([a-zA-Z_0-9\\.\\$]+?),");
		        Matcher matcher = pattern.matcher(search + ",");
		        while (matcher.find()) {
					logger.info("key" + matcher.group(1) + "oper" + matcher.group(2) + "val" + matcher.group(3));
		            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		        }
		         
		        Specification<Location> spec = builder.build();
				return locationService.getLocationsByCriteria(spec);
			} 
			else
			{
				logger.info("Read locations request received");
				return locationService.getAllLocations();
			}		
		} catch (Exception e) {
			logger.info("Error occurred while trying to process read locations request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/locations/{ids}", method = RequestMethod.GET)
	public Iterable<Location> getLocationsByID(@PathVariable List<Long> ids) {
		try {
			logger.info("Read locations by ids request received");
			return locationService.getLocationsByID(ids);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process api request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/locations", method = RequestMethod.PUT)
	public void updateLocation(@RequestBody Location location) {
		logger.info("Update location request received");

		try {
			locationService.saveLocation(location);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process update location request", e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE)
	public void deleteLocation(@PathVariable Integer id) {
		logger.info("Delete location request received");

		try {
			locationService.deleteLocation(id);
		} catch (Exception e) {
			logger.info("Error occurred while trying to process delete location request", e);
			throw new RuntimeException(e);
		}
	}
}
