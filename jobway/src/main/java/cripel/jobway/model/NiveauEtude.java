
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
@Table(name = "niveauetudefse", catalog = "jobway")
public class NiveauEtude implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idNiveauEtude", unique = true, nullable = false)
	private Integer idNiveauEtude;
	@Column(name = "niveauEtudeName")
	private String niveauEtudeName;
	@Column(name = "isDelete", nullable = false)
	private boolean isDelete;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "niveauEtude")
	private Set<FormationType> formationstype = new HashSet<>(0);
	public Integer getIdNiveauEtude() {
		return idNiveauEtude;
	}

	public void setIdNiveauEtude(Integer idNiveauEtude) {
		this.idNiveauEtude = idNiveauEtude;
	}

	public String getNiveauEtudeName() {
		return niveauEtudeName;
	}

	public void setNiveauEtudeName(String niveauEtudeName) {
		this.niveauEtudeName = niveauEtudeName;
	}

	public Set<FormationType> getFormationstype() {
		return formationstype;
	}

	public void setFormationstype(Set<FormationType> formationstype) {
		this.formationstype = formationstype;
	}

	@Override
	public String toString() {
		return this.niveauEtudeName;
	}

	public NiveauEtude() {
		super();
		
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
