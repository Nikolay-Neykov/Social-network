package people.explorer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category")
public class Category extends Model implements Serializable {

	private static final long serialVersionUID = -845384119937090343L;

	private Set<Location> locations;
	private Set<Event> events;

	public Category(String name, String description) {
		super(name, description);
	}
	
	public Category() {
	}

	@ManyToMany(mappedBy = "categories")
	@JsonBackReference("category_locations")
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	@ManyToMany(mappedBy = "categories")
	@JsonBackReference("category_events")
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
}
