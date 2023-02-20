
package cripel.jobway.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "acquis", catalog = "jobway")
public class Acquis implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAcquis", unique = true, nullable = false)
	private Integer idAcquis;
	@Column(name = "acquisName", nullable = false,length = 50)
	private String acquisName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acquis")
	private Set<Event> events = new HashSet<>(0);

	public Integer getIdAcquis()

	{
		return idAcquis;
	}

	public void setIdAcquis(Integer idAcquis) {
		this.idAcquis = idAcquis;
	}
	public String getAcquisName() {
		return acquisName;
	}

	public void setAcquisName(String acquisName) {
		this.acquisName = acquisName;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acquisName, events, idAcquis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acquis other = (Acquis) obj;
		return Objects.equals(acquisName, other.acquisName) && Objects.equals(events, other.events)
				&& Objects.equals(idAcquis, other.idAcquis);
	}

	
	@Override
	public String toString() {
		return this.acquisName;
	}

}
