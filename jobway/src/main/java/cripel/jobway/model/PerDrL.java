package cripel.jobway.model;

import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "per_drl", catalog = "jobway")
@AssociationOverrides({
		@AssociationOverride(name = "id.person", joinColumns = @JoinColumn(name = "idPerson", updatable = true, insertable = true)),
		@AssociationOverride(name = "id.driverLicense", joinColumns = @JoinColumn(name = "idDriverLicense", updatable = true, insertable = true)) })
/**
 * Class which represent the relation between {@link Person} and
 * {@link DriverLicense}
 */
public class PerDrL implements java.io.Serializable {
	private PerDrLId id = new PerDrLId();
	private boolean belgiumValid;

	public PerDrL() {
	}

	@EmbeddedId
	public PerDrLId getId() {
		return id;
	}

	public void setId(PerDrLId id) {
		this.id = id;
	}

	@Transient
	public DriverLicense getDriverLicense() {
		return getId().getDriverLicense();
	}

	public void setDriverLicense(DriverLicense driverLicense) {
		getId().setDriverLicense(driverLicense);
	}

	@Transient
	public Person getPerson() {
		return getId().getPerson();
	}

	public void setPerson(Person person) {
		getId().setPerson(person);
	}

	@Column(name = "belgiumValid", nullable = false)
	public boolean isBelgiumValid() {
		return this.belgiumValid;
	}

	public void setBelgiumValid(boolean belgiumValid) {
		this.belgiumValid = belgiumValid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		PerDrL other = (PerDrL) obj;
		return Objects.equals(id.getDriverLicense(), other.id.getDriverLicense());
	}

	@Override
	public String toString() {
		return id.getDriverLicense().toString();
	}

}
