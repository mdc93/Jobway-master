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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Familyreunion generated by hbm2java
 */
@Entity
@Table(name = "familyreunion", catalog = "jobway")
public class FamilyReunion implements java.io.Serializable {

	private Integer idFamilyReunion;
	private String familyReunionType;
	private boolean isDelete;
	private Set<Person> persons = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFamilyReunion", unique = true, nullable = false)
	public Integer getIdFamilyReunion() {
		return this.idFamilyReunion;
	}

	public void setIdFamilyReunion(Integer idFamilyReunion) {
		this.idFamilyReunion = idFamilyReunion;
	}

	@Column(name = "FamilyReunionType", nullable = false, length = 50)
	public String getFamilyReunionType() {
		return this.familyReunionType;
	}

	public void setFamilyReunionType(String familyReunionType) {
		this.familyReunionType = familyReunionType;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "familyreunions")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return this.familyReunionType;
	}
}
