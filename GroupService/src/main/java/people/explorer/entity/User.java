package people.explorer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 6135087477719651973L;

	private String id;
	private Set<UserGroup> groups;
	private Set<UserGroup> owner;

	public User(String id) {
		this.id = id;
	}
	
	public User() {
	}
	
	@Id
    @Column(unique = true, nullable = false)
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToMany(mappedBy = "users")
	@JsonBackReference("groups")
	public Set<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<UserGroup> groups) {
		this.groups = groups;
	}
	
	@OneToMany(mappedBy = "owner")
	@JsonBackReference("owner")
	public Set<UserGroup> getOwner() {
		return owner;
	}

	public void setOwner(Set<UserGroup> owner) {
		this.owner = owner;
	}
}