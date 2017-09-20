package people.explorer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "event")
public class Event extends Model implements Serializable {

	private static final long serialVersionUID = -845384119937090343L;

	private Date time;
	private String imageUrl;
	private Set<Category> categories;
	private Set<UserGroup> groups;
	private Location location;

	public Event(String name, String description, Date time, String imageUrl) {
		super(name, description);
		this.time = time;
		this.imageUrl = imageUrl;
	}
	
	public Event() {
	}

	@ManyToMany(mappedBy = "events")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId=true)
	public Set<UserGroup> getUserGroups() {
		return groups;
	}

	public void setUserGroups(Set<UserGroup> groups) {
		this.groups = groups;
	}

	@ManyToMany
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@ManyToOne
	@JoinColumn(name = "location_id")
	//@JsonBackReference("event_location")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId=true)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
