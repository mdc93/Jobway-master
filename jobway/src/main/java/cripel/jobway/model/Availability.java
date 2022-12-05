package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 *
 */
@Entity
@Table(name = "availability", catalog = "jobway")
public class Availability implements java.io.Serializable {

	/** Id of the availability */
	private Integer idAvailability;
	/** The name of the availability */
	private String availabilityName;
	/** Flag logic delete */
	private boolean isDelete;
	/** {@link ManyToMany} relation to {@link WorkSearch} */
	private Set<WorkSearch> worksearches = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idAvailability", unique = true, nullable = false)
	public Integer getIdAvailability() {
		return this.idAvailability;
	}

	public void setIdAvailability(Integer idAvailability) {
		this.idAvailability = idAvailability;
	}

	@Column(name = "availability", nullable = false, length = 50)
	public String getAvailability() {
		return this.availabilityName;
	}

	public void setAvailability(String availability) {
		this.availabilityName = availability;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "availabilities")
	public Set<WorkSearch> getWorksearches() {
		return this.worksearches;
	}

	public void setWorksearches(Set<WorkSearch> worksearches) {
		this.worksearches = worksearches;
	}

	@Override
	public String toString() {
		return this.availabilityName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAvailability);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Availability other = (Availability) obj;
		return Objects.equals(idAvailability, other.idAvailability);
	}

}
