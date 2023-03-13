package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Relation to represent the language spoke by a person ManyToMany relation
 * represented by two oneToMany {@link Person} to {@link Language}
 */
@Entity
@Table(name = "per_lan", catalog = "jobway")
@AssociationOverrides({
		@AssociationOverride(name = "id.language", joinColumns = @JoinColumn(name = "idLanguage", updatable = true, insertable = true)),
		@AssociationOverride(name = "id.person", joinColumns = @JoinColumn(name = "idPerson", updatable = true, insertable = true)) })
public class PerLan implements java.io.Serializable {

	/** Composite key */
	private PerLanId id = new PerLanId();
	/** Boolean to know if the person wish to communicate in this language */
	private boolean langCom;
	/** The level of the language (C1/C2) */
	private String langLevel;

	public PerLan() {
	}

	@EmbeddedId
	public PerLanId getId() {
		return this.id;
	}

	public void setId(PerLanId id) {
		this.id = id;
	}

	@Transient
	public Language getLanguage() {
		return getId().getLanguage();
	}

	public void setLanguage(Language language) {
		getId().setLanguage(language);
	}

	@Transient
	public Person getPerson() {
		return getId().getPerson();
	}

	public void setPerson(Person person) {
		getId().setPerson(person);
	}

	@Column(name = "langCom", nullable = false)
	public boolean isLangCom() {
		return this.langCom;
	}

	public void setLangCom(boolean langCom) {
		this.langCom = langCom;
	}

	@Column(name = "langLevel", nullable = false, length = 50)
	public String getLangLevel() {
		return this.langLevel;
	}

	public void setLangLevel(String langLevel) {
		this.langLevel = langLevel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "PerLan [id=" + id + ", langCom=" + langCom + ", langLevel=" + langLevel + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		PerLan other = (PerLan) obj;
		return Objects.equals(id.getLanguage(), other.id.getLanguage());
	}

	

}
