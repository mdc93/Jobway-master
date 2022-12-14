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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Countrytype generated by hbm2java
 */
@Entity
@Table(name = "countrytype", catalog = "jobway")
public class CountryType implements java.io.Serializable {

	private Integer idcountryType;
	private String countryTypeName;
	private boolean isDelete;
	private Set<Country> countries = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcountryType", unique = true, nullable = false)
	public Integer getIdcountryType() {
		return this.idcountryType;
	}

	public void setIdcountryType(Integer idcountryType) {
		this.idcountryType = idcountryType;
	}

	@Column(name = "countryTypeName", nullable = false, length = 50)
	public String getCountryTypeName() {
		return this.countryTypeName;
	}

	public void setCountryTypeName(String countryTypeName) {
		this.countryTypeName = countryTypeName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countrytype")
	public Set<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return this.countryTypeName;
	}

}
