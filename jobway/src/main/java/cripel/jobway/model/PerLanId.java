package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Composite key for {@link PerLan}
 */
@Embeddable
public class PerLanId implements java.io.Serializable {

	private Person person;
	private Language language;

	public PerLanId() {
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		int result;
		result = (language != null ? getLanguage().hashCode() : 0);
		result = 37 * result + (person != null ? person.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		PerLanId other = (PerLanId) obj;
		return Objects.equals(language, other.language) && Objects.equals(person, other.person);
	}

}
