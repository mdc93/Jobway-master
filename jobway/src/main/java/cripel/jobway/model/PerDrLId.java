package cripel.jobway.model;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * Composite key for {@link PerDrL}
 */
@Embeddable
public class PerDrLId implements java.io.Serializable {
	private DriverLicense driverLicense;
	private Person person;

	@ManyToOne
	public DriverLicense getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(DriverLicense driverLicense) {
		this.driverLicense = driverLicense;
	}

	@ManyToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		int result;
		result = (driverLicense != null ? getDriverLicense().hashCode() : 0);
		result = 37 * result + (person != null ? person.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		PerDrLId other = (PerDrLId) obj;
		return Objects.equals(driverLicense, other.driverLicense) && Objects.equals(person, other.person);
	}
}
