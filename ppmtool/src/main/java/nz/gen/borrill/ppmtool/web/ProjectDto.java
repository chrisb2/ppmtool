package nz.gen.borrill.ppmtool.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import nz.gen.borrill.ppmtool.domain.Project;

class ProjectDto {
	
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private String projectKey;
	
	private String projectName;
	
	private String description;
	
	@JsonFormat(pattern=DATE_FORMAT)
	private Date startDate;
	
	@JsonFormat(pattern=DATE_FORMAT)
	private Date endDate;

	@JsonFormat(pattern=DATE_TIME_FORMAT)
	private Date createdAt;
	
	@JsonFormat(pattern=DATE_TIME_FORMAT)
	private Date updatedAt;

	ProjectDto(Project project) {
		this.projectKey = project.getProjectKey();
		this.projectName = project.getProjectName();
		this.description = project.getDescription();
		this.startDate = project.getStartDate();
		this.endDate = project.getEndDate();
		this.createdAt = project.getCreatedAt();
		this.updatedAt = project.getUpdatedAt();
	}
	
	static List<ProjectDto> getAllProjects(Iterable<Project> projects) {
		List<ProjectDto> result = new ArrayList<>();
		projects.forEach(project -> result.add(new ProjectDto(project)));
		return result;
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
