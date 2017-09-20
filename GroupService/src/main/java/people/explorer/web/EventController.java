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

import people.explorer.entity.Event;
import people.explorer.service.CategoryService;
import people.explorer.service.EventService;
import people.explorer.service.LocationService;

@CrossOrigin(origins = "*")
@RestController
@EnableAutoConfiguration
public class EventController {

	    @Autowired
	    private EventService eventService;
	    
	    @Autowired
	    private LocationService locationService;
	    
	    @Autowired
	    private CategoryService categoryService;

	    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	    @RequestMapping(value = "/events", method = RequestMethod.POST)
		public void createEvent(@RequestBody Event event) {
			logger.info("Create event request received");

			try {
				eventService.saveEvent(event);
//				if (event.getLocation().getEvents() == null) {
//					event.getLocation().setEvents(Arrays.asList(event));
//				} else {
//					event.getLocation().getEvents().add(event);
//				}
//				
//				locationService.saveLocation(event.getLocation());
//				for (Category eventCategory : event.getCategories()) {
//					if (eventCategory.getEvents() == null) {
//						eventCategory.setEvents(new HashSet<Event>(Arrays.asList(event)));
//					} else {
//						eventCategory.getEvents().add(event);
//					}
//					categoryService.saveCategory(eventCategory);
//				}
			} catch (Exception e) {
				logger.info("Error occurred while trying to process create event request", e);
				throw new RuntimeException(e);
			}
		}
		
		@RequestMapping(value = "/events", method = RequestMethod.GET)
		public Iterable<Event> getAllEvents() {
			try {
				logger.info("Read events request received");
				return eventService.getAllEvents();
			} catch (Exception e) {
				logger.info("Error occurred while trying to process read events request", e);
				throw new RuntimeException(e);
			}
		}

		@RequestMapping(value = "/events/{ids}", method = RequestMethod.GET)
		public Iterable<Event> getEventsByID(@PathVariable List<Long> ids) {
			try {
				logger.info("Read events by ids request received");
				return eventService.getEventsByID(ids);		
			} catch (Exception e) {
				logger.info("Error occurred while trying to process api request", e);
				throw new RuntimeException(e);
			}
		}

		@RequestMapping(value = "/events", method = RequestMethod.PUT)
		public void updateEvent(@RequestBody Event event) {
			logger.info("Update event request received");

			try {
				eventService.saveEvent(event);
			} catch (Exception e) {
				logger.info("Error occurred while trying to process update event request", e);
				throw new RuntimeException(e);
			}
		}

		@RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
		public void deleteEvent(@PathVariable Integer id) {
			logger.info("Delete event request received");

			try {
				eventService.deleteEvent(id);
			} catch (Exception e) {
				logger.info("Error occurred while trying to process delete event request", e);
				throw new RuntimeException(e);
			}
		}	
}
