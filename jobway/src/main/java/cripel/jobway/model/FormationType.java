package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Formationtype generated by hbm2java
 */
@Entity
@Table(name = "formationtype", catalog = "jobway")
public class FormationType implements java.io.Serializable {

	private Integer idFormationType;
	private String formationTypeName;
	/** The event type. */
	private NiveauEtude niveauEtude;
	

	public void setNiveauEtude(NiveauEtude niveauEtude) {
		this.niveauEtude = niveauEtude;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idNiveauEtude")
	public NiveauEtude getNiveauEtude() {
		return niveauEtude;
	}
	private boolean isFormation;
	private boolean isDelete;
	private Set<Formation> formations = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFormationType", unique = true, nullable = false)
	public Integer getIdFormationType() {
		return this.idFormationType;
	}

	public void setIdFormationType(Integer idFormationType) {
		this.idFormationType = idFormationType;
	}

	@Column(name = "formationTypeName", nullable = false, length = 50)
	public String getFormationTypeName() {
		return this.formationTypeName;
	}

	public void setFormationTypeName(String formationTypeName) {
		this.formationTypeName = formationTypeName;
	}

	@Column(name = "isFormation", nullable = false)
	public boolean isIsFormation() {
		return this.isFormation;
	}

	public void setIsFormation(boolean isFormation) {
		this.isFormation = isFormation;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formationtype")
	public Set<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	@Override
	public String toString() {
		return this.formationTypeName;
	}

}
