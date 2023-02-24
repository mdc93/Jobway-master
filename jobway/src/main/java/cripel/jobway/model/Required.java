
package cripel.jobway.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "required", catalog = "jobway")
public class Required implements java.io.Serializable{ 

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idRequired", unique = true, nullable = false)
	private Integer idRequired;
	@Column(name = "requiredName")
	private String requiredName;
	@Column(name = "isDelete", nullable = false)
	private boolean isDelete;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "required")
	private Set<Event> events = new HashSet<>(0);

	public Integer getIdRequired() {
		return idRequired;
	}

	public void setIdRequired(Integer idRequired) {
		this.idRequired = idRequired;
	}

	public String getRequiredName() {
		return requiredName;
	}

	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Required() {
		super();
		
	}
	@Override
	public String toString() {
		return this.requiredName;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	

}
