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
@Table(name = "situationprofessionnelle", catalog = "jobway")
public class SituationProfessionnelle {
	
	
	private Integer idSituationProfessionnelle;
	private String situationProfessionnelleName;
	private boolean isDelete;
	private Set<Person> persons = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSituationProfessionnelle", unique = true, nullable = false)
	public Integer getIdSituationProfessionnelle() {
		return this.idSituationProfessionnelle;
	}

	public void setIdSituationProfessionnelle(Integer idSituationProfessionnelle) {
		this.idSituationProfessionnelle = idSituationProfessionnelle;
	}

	@Column(name = "situationProfessionnelleName", nullable = false, length = 50)
	public String getSituationProfessionnelleName() {
		return this.situationProfessionnelleName;
	}

	public void setSituationProfessionnelleName(String situationProfessionnelleName) {
		this.situationProfessionnelleName = situationProfessionnelleName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "situationprofessionnelle")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return this.getSituationProfessionnelleName();
	}
}
