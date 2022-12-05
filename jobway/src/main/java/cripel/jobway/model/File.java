package cripel.jobway.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The status of this person inscription
 *
 */
@Entity
@Table(name = "file", catalog = "jobway")
public class File {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFile", unique = true, nullable = false)
	/** The file id */
	private int idFile;
	@Temporal(TemporalType.DATE)
	@Column(name = "registrationDate")
	/** The date the person was first encoded in the database */
	private Date registrationDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "modificationDate")
	/** The last modification date */
	private Date modificationDate;
	@Column(name = "fileStatus")
	/** The status of the person inscription */
	private String fileStatus;
	@OneToOne(mappedBy = "file")
	/** The file's person */
	private Person person;

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return this.fileStatus;
	}

}
