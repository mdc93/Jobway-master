package cripel.jobway.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class where all Other choice who can't be put elsewhere are stocked
 *
 */
@Entity
@Table(name = "other", catalog = "jobway")
public class Other {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idOther", unique = true, nullable = false)
	/** the other id */
	private Integer idOther;
	@Column(name = "otherIncome")
	/** Other choice for {@link IncomeType} */
	private String otherIncome;
	@Column(name = "otherSituation")
	/** Other choice for {@link SituationTerritory} */
	private String otherSituation;
	@Column(name = "otherLanguageTest")
	/** Otber choice for {@link FrenchTest} */
	private String otherLanguageTest;
	@Column(name = "otherCivilStatus")
	/** Other choice for {@link CivilStatus} */
	private String otherCivilStatus;
	@OneToOne(mappedBy = "other")
	/** the person who own this entry */
	private Person person;

	public Integer getIdOther() {
		return idOther;
	}

	public void setIdOther(Integer idOther) {
		this.idOther = idOther;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getOtherSituation() {
		return otherSituation;
	}

	public void setOtherSituation(String otherSituation) {
		this.otherSituation = otherSituation;
	}

	public String getOtherLanguageTest() {
		return otherLanguageTest;
	}

	public void setOtherLanguageTest(String otherLanguageTest) {
		this.otherLanguageTest = otherLanguageTest;
	}

	public String getOtherCivilStatus() {
		return otherCivilStatus;
	}

	public void setOtherCivilStatus(String otherCivilStatus) {
		this.otherCivilStatus = otherCivilStatus;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
