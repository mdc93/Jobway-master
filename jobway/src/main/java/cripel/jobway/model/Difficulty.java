
package cripel.jobway.model;
//Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "difficulty", catalog = "jobway")
public class Difficulty implements java.io.Serializable {
	
	private Integer idDifficulty;
	private String difficultyName;
	private boolean isDelete;
	private Set<Person> persons = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idDifficulty", unique = true, nullable = false)
	public Integer getIdDifficulty() {
		return this.idDifficulty;
	}

	public void setIdDifficulty(Integer idDifficulty) {
		this.idDifficulty = idDifficulty;
	}

	@Column(name = "difficultyName", nullable = false, length = 50)
	public String getDifficultyName() {
		return this.difficultyName;
	}

	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@ManyToMany(mappedBy = "difficulties")
	public Set<Person> getPersons() {
		return this.persons;
	}
	
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return this.difficultyName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(difficultyName, idDifficulty, isDelete, persons);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Difficulty other = (Difficulty) obj;
		return Objects.equals(difficultyName, other.difficultyName) && Objects.equals(idDifficulty, other.idDifficulty)
				&& isDelete == other.isDelete && Objects.equals(persons, other.persons);
	}
	

}