package nz.gen.borrill.ppmtool.web;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import nz.gen.borrill.ppmtool.domain.Project;

class UpdateProjectDto {

	@NotBlank(message="Project name is required")
	private String projectName;
	
	@NotBlank(message="Project description is required")
	private String description;
	
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date startDate;
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date endDate;
	
    UpdateProjectDto() {}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	
	Project getProject(String projectKey) {
		return new Project(projectKey, this.projectName, this.description, this.startDate, this.endDate);
	}

}
