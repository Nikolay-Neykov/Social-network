package people.explorer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user_group")
public class UserGroup extends Model implements Serializable {

	private static final long serialVersionUID = 6135087477719651973L;

	private Set<Event> events;
	private User owner;
	private Set<User> users;

	public UserGroup(String name, String description) {
		super(name, description);
	}
	
	public UserGroup() {
	}

	@ManyToMany
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	//@JsonIdentityReference(alwaysAsId=true)
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@ManyToMany
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}