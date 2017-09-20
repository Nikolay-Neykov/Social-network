package people.explorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import people.explorer.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
}