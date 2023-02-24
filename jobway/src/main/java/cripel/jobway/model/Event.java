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
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEvent", unique = true, nullable = false)
	private Integer idEvent;

	/** The event type. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEventType")
	private EventType eventType;

	/** The person which the event belong */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPerson", nullable = false)
	private Person person;

	/** The theme of the event */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTheme", nullable = false)
	private Theme theme;

	/** The event date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "eventDate", nullable = false, length = 10)
	private Date eventDate;

	/** The event duration. */
	@Column(name = "eventDuration")
	private Integer eventDuration;

	/** The event note. */
	@Column(name = "eventNote", length = 100)
	private String eventNote;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idExitType", nullable = false)
	private ExitType exittype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRequired", nullable = false)
	private Required required;
	/** The employees that participated in the event */

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "emp_eve", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idEvent", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idEmployee", nullable = false, updatable = false) })
	private Set<Employee> employees = new HashSet<>(0);

	@Column(name = "exitEvent",length = 2)
	private Boolean exitEvent;

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(Integer eventDuration) {
		this.eventDuration = eventDuration;
	}

	public String getEventNote() {
		return eventNote;
	}

	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}

	public ExitType getExittype() {
		return exittype;
	}

	public void setExittype(ExitType exittype) {
		this.exittype = exittype;
	}

	public Required getRequired() {
		return required;
	}

	public void setRequired(Required required) {
		this.required = required;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Boolean getExitEvent() {
		return exitEvent;
	}

	public void setExitEvent(Boolean exitEvent) {
		this.exitEvent = exitEvent;
	}

	public Event() {
		super();
		
	}

	

	

}
