package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import javafx.scene.paint.Color;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Person entity central in the database
 */
@Entity
@Table(name = "person", catalog = "jobway")
public class Person implements java.io.Serializable {


	private String niveauEtude;

	/** The id person. */
	private Integer idPerson;

	/** The civilstatus. */
	private CivilStatus civilstatus;

	/** The country relation if a person is a refugee */
	private Country countryByIdCountry;

	/** The country relation if a person has a family reunion */
	private Country countryByIdCountryReunionNationality;

	/** Relation one to one */
	private Household household;

	/** The situation territory. */
	private SituationTerritory situationterritory;

	/** The worksearch information */
	private WorkSearch worksearch;

	/** The file. */
	private File file;

	/** The french test passed by the person */
	private FrenchTest frenchTest;

	/** The city where the person live */
	private City city;

//	private NiveauEtudeFSE niveauEtudeFSE;
//
//	private SituationProfFSE situationProfFSE;

	/** OneToOne to represent other choice of the person */
	private Other other;

	/** OnetoOne representation of the person difficulty */
	private Disability disability;

	/** The employee who manage this person */
	private Employee employee;

	/** The person first name. */
	private String personFirstName;

	/** The person last name. */
	private String personLastName;

	/** The person National security number. */
	private String personNiss;

	/** The person address. */
	private String personAddress;

	/** The person phone. */
	private String personPhone;

	/** The person birth date. */
	private Date personBirthDate;

	/** The person mail. */
	private String personMail;

	/** The person gender. */
	private String personGender;

	/** The person arrival date. */
	private Date personArrivalDate;

	/** The person healthcare name. */
	private String personHealthcare;

	/** Show if the person want to receive a newsletter. */
	private Boolean personNewsLetterWork;

	/** The person unemployement duration. */
	private String personUnemployementDuration;

	/** The person inscription date to Forem. */
	private Date personForemInsDate;

	/** The person orientation, who send them to Cripel. */
	private String personOrientation;

	/** The person orientation note idk. */
	private String personOrientationNote;

	/** The person notes about his work access. */
	private String personNotes;

	/** The person is delete flag. */
	private Boolean personIsDelete;

	/** The boolean to know if the person received the belgian nationality. */
	private Boolean personIsBelgian;

	/** Do the person has a vehicle. */
	private Boolean personHasVehicle;

	/** The person work access. */
	private Boolean personWorkAccess;

	/** The person notepad. */
	private String personNotepad;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSituationPro", nullable = true)
	private SituationPro situationPro;

	/** The locomotion means. */
	private Set<LocomotionMean> locomotionmeans = new HashSet<>(0);

	/** The formations. */
	private Set<Formation> formations = new HashSet<>(0);

	/** The Income source of the person. */
	private Set<IncomeType> incometypes = new HashSet<>(0);

	/** The Driver License belonging to the person. */
	private Set<PerDrL> perDrLs = new HashSet<>(0);

	/** The residence permits of the person */
	private Set<ResidencePermit> residencepermits = new HashSet<>(0);

	/** The Language spoke by the person */
	private Set<PerLan> perLans = new HashSet<>(0);

	/** The nationalities */
	private Set<Country> countries = new HashSet<>(0);

	/** The person's family degree they joined in the country */
	private Set<FamilyReunion> familyreunions = new HashSet<>(0);

	/** Unused */
	private Set<PerSkill> perSkills = new HashSet<>(0);

	/** The events to this person */
	private Set<Event> events = new HashSet<>(0);

	/** The workexperiences of the person. */
	private Set<WorkExperience> workexperiences = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPerson", unique = true, nullable = false)
	public Integer getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCivilStatus", nullable = true)
	public CivilStatus getCivilstatus() {
		return this.civilstatus;
	}

	public void setCivilstatus(CivilStatus civilstatus) {
		this.civilstatus = civilstatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCountry", nullable = true)
	public Country getCountryByIdCountry() {
		return this.countryByIdCountry;
	}

	public void setCountryByIdCountry(Country countryByIdCountry) {
		this.countryByIdCountry = countryByIdCountry;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCountry_reunionNationality")
	public Country getCountryByIdCountryReunionNationality() {
		return this.countryByIdCountryReunionNationality;
	}

	public void setCountryByIdCountryReunionNationality(Country countryByIdCountryReunionNationality) {
		this.countryByIdCountryReunionNationality = countryByIdCountryReunionNationality;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idHousehold", nullable = true)
	public Household getHousehold() {
		return this.household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSituationTerritory", nullable = true)
	public SituationTerritory getSituationterritory() {
		return this.situationterritory;
	}

	public void setSituationterritory(SituationTerritory situationterritory) {
		this.situationterritory = situationterritory;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idWorkSearch")
	public WorkSearch getWorksearch() {
		return this.worksearch;
	}

	public void setWorksearch(WorkSearch worksearch) {
		this.worksearch = worksearch;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCity")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "idNiveauEtudeFSE")
//	public NiveauEtudeFSE getNiveauEtudeFSE() {
//		return niveauEtudeFSE;
//	}
//
//
//	public void setNiveauEtudeFSE(NiveauEtudeFSE niveauEtudeFSE) {
//		this.niveauEtudeFSE = niveauEtudeFSE;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "idNiveauEtudeFSE")
//	public SituationProfFSE getSituationProfFSE() {
//		return situationProfFSE;
//	}
//
//	public void setSituationProfFSE(SituationProfFSE situationProfFSE) {
//		this.situationProfFSE = situationProfFSE;
//	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idOther")
	public Other getOther() {
		return other;
	}

	public void setOther(Other other) {
		this.other = other;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idFile")
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFrenchTest")
	public FrenchTest getFrenchTest() {
		return frenchTest;
	}

	public void setFrenchTest(FrenchTest frenchTest) {
		this.frenchTest = frenchTest;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idDisability")
	public Disability getDisability() {
		return disability;
	}

	public void setDisability(Disability disability) {
		this.disability = disability;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idEmployee")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "personFirstName", nullable = true, length = 50)
	public String getPersonFirstName() {
		return this.personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	@Column(name = "personLastName", nullable = true, length = 50)
	public String getPersonLastName() {
		return this.personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	@Column(name = "personNISS", nullable = true, length = 50)
	public String getPersonNiss() {
		return this.personNiss;
	}

	public void setPersonNiss(String personNiss) {
		this.personNiss = personNiss;
	}

	@Column(name = "personAddress", nullable = true, length = 100)
	public String getPersonAddress() {
		return this.personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	@Column(name = "personPhone", nullable = true, length = 50)
	public String getPersonPhone() {
		return this.personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "personBirthDate", nullable = true, length = 10)
	public Date getPersonBirthDate() {
		return this.personBirthDate;
	}

	public void setPersonBirthDate(Date personBirthDate) {
		this.personBirthDate = personBirthDate;
	}

	@Column(name = "personMail", nullable = true, length = 100)
	public String getPersonMail() {
		return this.personMail;
	}

	public void setPersonMail(String personMail) {
		this.personMail = personMail;
	}

	@Column(name = "personGender", nullable = true, length = 10)
	public String getPersonGender() {
		return this.personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "personArrivalDate", nullable = true, length = 10)
	public Date getPersonArrivalDate() {
		return this.personArrivalDate;
	}

	public void setPersonArrivalDate(Date personArrivalDate) {
		this.personArrivalDate = personArrivalDate;
	}

	@Column(name = "personHealthcare")
	public String getPersonHealthcare() {
		return this.personHealthcare;
	}

	public void setPersonHealthcare(String personHealthcare) {
		this.personHealthcare = personHealthcare;
	}

	@Column(name = "personNewsLetterWork", nullable = true)
	public Boolean isPersonNewsLetterWork() {
		return this.personNewsLetterWork;
	}

	public void setPersonNewsLetterWork(Boolean personNewsLetterWork) {
		this.personNewsLetterWork = personNewsLetterWork;
	}

	@Column(name = "personNotepad", nullable = true)
	public String getPersonNotepad() {
		return personNotepad;
	}

	public void setPersonNotepad(String personNotepad) {
		this.personNotepad = personNotepad;
	}

	@Column(name = "personUnemployementDuration", nullable = true)
	public String getPersonUnemployementDuration() {
		return this.personUnemployementDuration;
	}

	public void setPersonUnemployementDuration(String personUnemployementDuration) {
		this.personUnemployementDuration = personUnemployementDuration;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "personForemInsDate", length = 10)
	public Date getPersonForemInsDate() {
		return this.personForemInsDate;
	}

	public void setPersonForemInsDate(Date personForemInsDate) {
		this.personForemInsDate = personForemInsDate;
	}

	@Column(name = "personOrientation", nullable = true, length = 50)
	public String getPersonOrientation() {
		return this.personOrientation;
	}

	public void setPersonOrientation(String personOrientation) {
		this.personOrientation = personOrientation;
	}

	@Column(name = "personIsDelete", nullable = true)
	public Boolean isPersonIsDelete() {
		return this.personIsDelete;
	}

	public void setPersonIsDelete(Boolean personIsDelete) {
		this.personIsDelete = personIsDelete;
	}

	@Column(name = "personWorkAccess", nullable = true)
	public Boolean isPersonWorkAccess() {
		return this.personWorkAccess;
	}

	public void setPersonWorkAccess(Boolean personWorkAccess) {
		this.personWorkAccess = personWorkAccess;
	}

	@Column(name = "personIsBelgian", nullable = true)
	public Boolean isPersonIsBelgian() {
		return this.personIsBelgian;
	}

	public void setPersonIsBelgian(Boolean personIsBelgian) {
		this.personIsBelgian = personIsBelgian;
	}

	@Column(name = "personNotes", nullable = true, length = 100)
	public String getPersonNotes() {
		return this.personNotes;
	}

	public void setPersonNotes(String personNotes) {
		this.personNotes = personNotes;
	}

	@Column(name = "personHasVehicle", nullable = true)
	public Boolean isPersonHasVehicle() {
		return this.personHasVehicle;
	}

	public void setPersonHasVehicle(Boolean personHasVehicle) {
		this.personHasVehicle = personHasVehicle;
	}

	@Column(name = "personOrientationNote", nullable = true, length = 100)
	public String getPersonOrientationNote() {
		return this.personOrientationNote;
	}

	public void setPersonOrientationNote(String personOrientationNote) {
		this.personOrientationNote = personOrientationNote;
	}

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "per_loc", joinColumns = { @JoinColumn(name = "idPerson") }, inverseJoinColumns = {
			@JoinColumn(name = "idLocomotionMean") })
	public Set<LocomotionMean> getLocomotionmeans() {
		return this.locomotionmeans;
	}

	public void setLocomotionmeans(Set<LocomotionMean> locomotionmeans) {
		this.locomotionmeans = locomotionmeans;
	}

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "per_inc", joinColumns = {
			@JoinColumn(name = "idPerson", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idIncomeType", nullable = false) })
	public Set<IncomeType> getIncometypes() {
		return this.incometypes;
	}

	public void setIncometypes(Set<IncomeType> incometypes) {
		this.incometypes = incometypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.person", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PerDrL> getPerDrL() {
		return this.perDrLs;
	}

	public void setPerDrL(Set<PerDrL> perDrLs) {
		this.perDrLs = perDrLs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	public Set<ResidencePermit> getResidencepermits() {
		return this.residencepermits;
	}

	public void setResidencepermits(Set<ResidencePermit> residencepermits) {
		this.residencepermits = residencepermits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.person", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PerLan> getPerLans() {
		return this.perLans;
	}

	public void setPerLans(Set<PerLan> perLans) {
		this.perLans = perLans;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "nationality", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idPerson", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idCountry", nullable = false, updatable = false) })
	public Set<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "reunion", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idPerson", nullable = true, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idFamilyReunion", nullable = true, updatable = false) })
	public Set<FamilyReunion> getFamilyreunions() {
		return this.familyreunions;
	}

	public void setFamilyreunions(Set<FamilyReunion> familyreunions) {
		this.familyreunions = familyreunions;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "per_for", catalog = "jobway", joinColumns = {
			@JoinColumn(name = "idPerson", nullable = false, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "idFormation", nullable = false, updatable = true) })
	public Set<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.person", cascade = CascadeType.ALL)
	public Set<PerSkill> getPerSkills() {
		return this.perSkills;
	}

	public void setPerSkills(Set<PerSkill> perSkills) {
		this.perSkills = perSkills;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<WorkExperience> getWorkexperiences() {
		return this.workexperiences;
	}

	public void setWorkexperiences(Set<WorkExperience> workexperiences) {
		this.workexperiences = workexperiences;
	}

	//pardon, transient pour ne pas perturber Hibernate, vu que cette colonne n'est pas dans la DB
	//cette méthode est là juste pour l'export.
	@Transient
	public String getNiveauEtude() {

		//OK c'est triché de faire ça
		int idTypeFormation = this.formations.stream()
				.mapToInt(formation -> formation.getFormationtype().getIdFormationType())
				.sum();

		switch(idTypeFormation)
		{
			case 1: case 2: case 3:
			setNiveauEtude("Max. 1er cycle du secondaire");
			break;
			case 4: case 5:
			setNiveauEtude("Max. enseignement post-secondaire non supérieur");
			break;
			case 6: case 7: case 8: case 9:
			setNiveauEtude("Enseignement supérieur");
			break;
			default:
				setNiveauEtude(" ");
				break;
		}

		return niveauEtude;
	}

	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}
}
