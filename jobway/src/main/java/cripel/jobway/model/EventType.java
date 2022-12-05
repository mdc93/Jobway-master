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
@Table(name = "eventtype", catalog = "jobway")
public class EventType {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idEventType", unique = true, nullable = false)
	private Integer idEventType;
	@Column(name = "eventTypeName")
	private String eventTypeName;
	@Column(name = "isDelete")
	private boolean isDelete;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventType")
	private Set<Event> events = new HashSet<>(0);

	public Integer getIdEventType() {
		return idEventType;
	}

	public void setIdEventType(Integer idEventType) {
		this.idEventType = idEventType;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return this.eventTypeName;
	}

}
