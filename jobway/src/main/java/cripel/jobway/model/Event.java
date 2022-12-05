package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * All the events that happen to a person.
 */
@Entity
@Table(name = "event", catalog = "jobway")
public class Event implements java.io.Serializable {

	/** The id event. */
	private Integer idEvent;

	/** The event type. */
	private EventType eventType;

	/** The person which the event belong */
	private Person person;

	/** The theme of the event */
	private Theme theme;

	/** The event date. */
	private Date eventDate;

	/** The event duration. */
	private Integer eventDuration;

	/** The event note. */
	private String eventNote;

	/** The employees that participated in the event */
	private Set<Employee> employees = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEvent", unique = true, nullable = false)
	public Integer getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEventType")
	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPerson", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTheme", nullable = false)
	public Theme getTheme() {
		return this.theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "eventDate", nullable = false, length = 10)
	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Column(name = "eventDuration")
	public Integer getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(Integer eventDuration) {
		this.eventDuration = eventDuration;
	}

	@Column(name = "eventNote", length = 100)
	public String getEventNote() {
		return eventNote;
	}

	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "emp_eve", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idEvent", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idEmployee", nullable = false, updatable = false) })
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
