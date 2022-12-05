package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "country", catalog = "jobway")
public class Country {

	private Integer idCountry;
	private CountryType countrytype;
	private String countryName;
	private boolean isDelete;
	private Set<Person> personsForIdCountry = new HashSet<>(0);
	private Set<Person> personsForIdCountryReunionNationality = new HashSet<>(0);
	private Set<Person> persons = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCountry", unique = true, nullable = false)
	public Integer getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcountryType", nullable = false)
	public CountryType getCountrytype() {
		return this.countrytype;
	}

	public void setCountrytype(CountryType countrytype) {
		this.countrytype = countrytype;
	}

	@Column(name = "countryName", nullable = false, length = 50)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryByIdCountry")
	public Set<Person> getPersonsForIdCountry() {
		return this.personsForIdCountry;
	}

	public void setPersonsForIdCountry(Set<Person> personsForIdCountry) {
		this.personsForIdCountry = personsForIdCountry;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countryByIdCountryReunionNationality")
	public Set<Person> getPersonsForIdCountryReunionNationality() {
		return this.personsForIdCountryReunionNationality;
	}

	public void setPersonsForIdCountryReunionNationality(Set<Person> personsForIdCountryReunionNationality) {
		this.personsForIdCountryReunionNationality = personsForIdCountryReunionNationality;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "countries")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return this.countryName;
	}
}
