package nz.gen.borrill.ppmtool.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import nz.gen.borrill.ppmtool.domain.Project;

class NewProjectDto extends UpdateProjectDto {
	
	@NotBlank
	@Size(min=4, max=5, message="Project key must be 4 to 5 characters")
	private String projectKey;
		
	NewProjectDto() {}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	
	Project getProject() {
		return new Project(this.projectKey, getProjectName(), getDescription(), getStartDate(), getEndDate());
	}

}
