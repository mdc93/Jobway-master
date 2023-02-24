
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
@Table(name = "exittype", catalog = "jobway")
public class ExitType implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idExitType", unique = true, nullable = false)
	private Integer idExitType;
	@Column(name = "exitTypeName")
	private String exitTypeName;
	@Column(name = "isDelete", nullable = false)
	private boolean isDelete;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exittype")
	private Set<Event> events = new HashSet<>(0);

	public Integer getIdExitType() {
		return idExitType;
	}

	public void setIdExitType(Integer idExitType) {
		this.idExitType = idExitType;
	}

	public String getExitTypeName() {
		return exitTypeName;
	}

	public void setExitTypeName(String exitTypeName) {
		this.exitTypeName = exitTypeName;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public ExitType() {
		super();
		
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return this.exitTypeName;
	}

	

	

}
