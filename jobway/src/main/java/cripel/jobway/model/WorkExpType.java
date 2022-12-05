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
 * The Class WorkExpType.
 */
@Entity
@Table(name = "workexptype", catalog = "jobway")
public class WorkExpType {

	/** The id of the work exp type. */
	private Integer idWorkExpType;

	/** The work experience type name. */
	private String workExpTypeName;

	/** The is delete flag. */
	private boolean isDelete;

	/** The workexperiences. */
	private Set<WorkExperience> workexperiences = new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idWorkExpType", unique = true, nullable = false)
	public Integer getIdWorkExpType() {
		return this.idWorkExpType;
	}

	public void setIdWorkExpType(Integer idWorkExpType) {
		this.idWorkExpType = idWorkExpType;
	}

	@Column(name = "workExpTypeName", nullable = false, length = 50)
	public String getWorkExpTypeName() {
		return this.workExpTypeName;
	}

	public void setWorkExpTypeName(String workExpTypeName) {
		this.workExpTypeName = workExpTypeName;
	}

	@Column(name = "isDelete", nullable = false)
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workexptype")
	public Set<WorkExperience> getWorkexperiences() {
		return this.workexperiences;
	}

	public void setWorkexperiences(Set<WorkExperience> workexperiences) {
		this.workexperiences = workexperiences;
	}

	@Override
	public String toString() {
		return this.workExpTypeName;
	}

}
