package cripel.jobway.model;
// Generated Feb 25, 2022, 8:26:41 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class to represent the tasks that a person did in a {@link WorkExperience}
 *
 */
@Entity
@Table(name = "worktask", catalog = "jobway")
public class WorkTask {

	private Integer idWorkTask;
	private WorkExperience workexperience;
	private String workTaskDescription;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idWorkTask", unique = true, nullable = false)
	public Integer getIdWorkTask() {
		return this.idWorkTask;
	}

	public void setIdWorkTask(Integer idWorkTask) {
		this.idWorkTask = idWorkTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idWorkExp", nullable = false)
	public WorkExperience getWorkexperience() {
		return this.workexperience;
	}

	public void setWorkexperience(WorkExperience workexperience) {
		this.workexperience = workexperience;
	}

	@Column(name = "workTaskDescription", nullable = false, length = 65535)
	public String getWorkTaskDescription() {
		return this.workTaskDescription;
	}

	public void setWorkTaskDescription(String workTaskDescription) {
		this.workTaskDescription = workTaskDescription;
	}

	@Override
	public String toString() {
		return this.workTaskDescription;
	}

}
