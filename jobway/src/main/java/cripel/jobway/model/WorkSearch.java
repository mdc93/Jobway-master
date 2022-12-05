package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Class to represent a person status in his job seeking
 */
@Entity
@Table(name = "worksearch", catalog = "jobway")
public class WorkSearch {

	/** The id work search. */
	private Integer idWorkSearch;

	/** Boolean to check if a person is looking for a job at the moment. */
	private Boolean isSearching;

	/** Column to display other information about a job search */
	private String workSearchAnnex;

	/**
	 * The work search other availability if the choice in {@link Availability}
	 * aren't enough
	 */
	private String workSearchOtherAvailibility;

	/** The person Set for oneToMany relation */
	private Set<Person> persons = new HashSet<>(0);

	/** The availabilities a person want for the job they're looking for */
	private Set<Availability> availabilities = new HashSet<>(0);

	/** The work sectors the person want a job in */
	private Set<WorkSector> workSectors = new HashSet<>(0);

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "sea_sec", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idWorkSearch", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idWorkSector", nullable = false, updatable = false) })
	public Set<WorkSector> getWorkSectors() {
		return this.workSectors;
	}

	public void setWorkSectors(Set<WorkSector> workSectors) {
		this.workSectors = workSectors;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idWorkSearch", unique = true, nullable = false)
	public Integer getIdWorkSearch() {
		return this.idWorkSearch;
	}

	public void setIdWorkSearch(Integer idWorkSearch) {
		this.idWorkSearch = idWorkSearch;
	}

	/**
	 * @return the isSearching
	 */
	@Column(name = "isSearching")
	public Boolean isSearching() {
		return isSearching;
	}

	/**
	 * @param isSearching the isSearching to set
	 */

	public void setSearching(Boolean isSearching) {
		this.isSearching = isSearching;
	}

	/**
	 * @return the workSearchAnnex
	 */
	@Column(name = "workSearchAnnex")
	public String getWorkSearchAnnex() {
		return workSearchAnnex;
	}

	/**
	 * @param workSearchAnnex the workSearchAnnex to set
	 */
	public void setWorkSearchAnnex(String workSearchAnnex) {
		this.workSearchAnnex = workSearchAnnex;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worksearch")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "sea_avai", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idWorkSearch", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idAvailability", nullable = false, updatable = false) })
	public Set<Availability> getAvailabilities() {
		return this.availabilities;
	}

	public void setAvailabilities(Set<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	@Column(name = "workSearchOtherAvailability")
	public String getWorkSearchOtherAvailibility() {
		return workSearchOtherAvailibility;
	}

	public void setWorkSearchOtherAvailibility(String workSearchOtherAvailibility) {
		this.workSearchOtherAvailibility = workSearchOtherAvailibility;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idWorkSearch);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		WorkSearch other = (WorkSearch) obj;
		return Objects.equals(idWorkSearch, other.idWorkSearch);
	}

}
