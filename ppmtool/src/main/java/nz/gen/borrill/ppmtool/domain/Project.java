package nz.gen.borrill.ppmtool.domain;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Project name is required")
	private String projectName;
	
	@Embedded
	private ProjectIdentifier projectIdentifier;
	
	@NotBlank(message="Project description is required")
	private String description;
	
	@JsonFormat(pattern=DATE_FORMAT)
	private Date startDate;
	@JsonFormat(pattern=DATE_FORMAT)
	private Date endDate;
	@JsonFormat(pattern=DATE_FORMAT)
	private Date createdAt;
	@JsonFormat(pattern=DATE_FORMAT)
	private Date updatedAt;
	
	public Project() {}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier.getId();
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = new ProjectIdentifier(projectIdentifier);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
