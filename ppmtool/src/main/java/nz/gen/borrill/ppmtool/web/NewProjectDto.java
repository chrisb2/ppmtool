package nz.gen.borrill.ppmtool.web;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import nz.gen.borrill.ppmtool.domain.Project;

class NewProjectDto {
	
	@NotBlank
	@Size(min=4, max=5, message="Project key must be 4 to 5 characters")
	private String projectKey;
	
	@NotBlank(message="Project name is required")
	private String projectName;
	
	@NotBlank(message="Project description is required")
	private String description;
	
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date startDate;
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date endDate;
	
	NewProjectDto() {}

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
	
	Project getProject() {
		return new Project(this.projectKey, this.projectName, this.description, this.startDate, this.endDate);
	}

}
