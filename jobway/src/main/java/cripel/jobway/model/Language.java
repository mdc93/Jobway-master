package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Class which represent all language in the database
 */
@Entity
@Table(name = "language", catalog = "jobway")
public class Language implements java.io.Serializable {

	/** The id language. */
	private Integer idLanguage;

	/** The language name. */
	private String languageName;

	/** The flag delete. */
	private boolean isDelete;

	/** The Person to Language composite key */
	private Set<PerLan> perLans = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idLanguage", unique = true, nullable = false)
	public Integer getIdLanguage() {
		return this.idLanguage;
	}

	public void setIdLanguage(Integer idLanguage) {
		this.idLanguage = idLanguage;
	}

	@Column(name = "languageName", nullable = false, length = 50)
	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.language", cascade = CascadeType.ALL)
	public Set<PerLan> getPerLans() {
		return this.perLans;
	}

	public void setPerLans(Set<PerLan> perLans) {
		this.perLans = perLans;
	}

	@Override
	public String toString() {
		return this.languageName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Language other = (Language) obj;
		return Objects.equals(languageName, other.languageName);
	}

	public Language() {
		super();
	}

	
}
