
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
@Table(name = "niveauetudesfe", catalog = "jobway")
public class NiveauEtude {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idNiveauType", unique = true, nullable = false)
	private Integer idNiveauType;
	@Column(name = "niveauTypeName")
	private String niveauTypeName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "niveauEtude")
	private Set<FormationType> formationstype = new HashSet<>(0);
	public Integer getIdNiveauType() {
		return idNiveauType;
	}

	public void setIdNiveauType(Integer idNiveauType) {
		this.idNiveauType = idNiveauType;
	}

	public Set<FormationType> getFormationstype() {
		return formationstype;
	}

	public void setFormationstype(Set<FormationType> formationstype) {
		this.formationstype = formationstype;
	}

	@Override
	public String toString() {
		return this.niveauTypeName;
	}

}
