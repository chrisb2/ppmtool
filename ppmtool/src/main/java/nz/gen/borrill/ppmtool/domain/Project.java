package nz.gen.borrill.ppmtool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_project_key", columnNames= { "projectKey" } ))
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String projectName;
	
	@Column(nullable=false, updatable=false)
	private String projectKey;
	
	@Column(nullable=false)
	private String description;
	
	private Date startDate;
	private Date endDate;
	@Column(nullable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public Project() {}
	
	public Project(String projectKey, String projectName, String description, Date startDate, Date endDate) {
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void updateFromExisting(Project existing) {
		setId(existing.getId());
		setCreatedAt(existing.getCreatedAt());
	}
	

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

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
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

	private void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

}
