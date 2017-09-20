package people.explorer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import people.explorer.entity.Event;
import people.explorer.repository.EventRepository;

@Transactional
@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public void saveEvent(Event event) {
		eventRepository.save(event);
	}
	
	public Event getEvent(long id) {
		return eventRepository.findOne(id);
	}

	public Iterable<Event> getEventsByID(Iterable<Long> ids) {
		return eventRepository.findAll(ids);
	}
	
	public Iterable<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public void deleteEvent(long id) {
		eventRepository.delete(id);
	}
}
