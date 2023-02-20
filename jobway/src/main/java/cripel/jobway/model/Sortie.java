
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
@Table(name = "sortie", catalog = "jobway")
public class Sortie implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSortie", unique = true, nullable = false)
	private Integer idSortie;
	@Column(name = "sortieName")
	private String sortieName;
	@Column(name = "isDelete", nullable = false)
	private boolean isDelete;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sortie")
	private Set<Event> events = new HashSet<>(0);

	public Sortie() {
		super();
	
	}

	public Integer getIdSortie()

	{
		return idSortie;
	}

	public void setIdsortie(Integer idSortie) {
		this.idSortie = idSortie;
	}

	public String getSortieName() {
		return sortieName;
	}

	public void setSortieName(String sortieName) {
		this.sortieName = sortieName;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setIdSortie(Integer idSortie) {
		this.idSortie = idSortie;
	}

	public Set<Event> getEvent() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		return Objects.hash(events, idSortie, sortieName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sortie other = (Sortie) obj;
		return Objects.equals(events, other.events) && Objects.equals(idSortie, other.idSortie)
				&& Objects.equals(sortieName, other.sortieName);
	}

	public String toString() {
		return this.sortieName;
	}

}
