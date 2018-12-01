package nz.gen.borrill.ppmtool.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import nz.gen.borrill.ppmtool.domain.Project;

class ProjectDto {
	
	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	private String projectKey;
	
	private String projectName;
	
	private String description;
	
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date startDate;
	
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date endDate;

	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date createdAt;
	
	@JsonFormat(pattern=ProjectDto.DATE_FORMAT)
	private Date updatedAt;

	public ProjectDto(Project project) {
		this.projectKey = project.getProjectKey();
		this.projectName = project.getProjectName();
		this.description = project.getDescription();
		this.startDate = project.getStartDate();
		this.endDate = project.getEndDate();
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
	}

	public String getProjectKey() {
		return projectKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
}
