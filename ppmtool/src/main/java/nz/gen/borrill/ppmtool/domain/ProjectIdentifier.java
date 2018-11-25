package nz.gen.borrill.ppmtool.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class ProjectIdentifier {
	
	@NotBlank(message="Project identifier is required")
	@Size(min=4, max=5, message="Please use 4 to 5 characters")
	@Column(name="projectIdentifier", updatable=false, unique=true)	
	private String projectId;
	
	public ProjectIdentifier() {}
	

	public ProjectIdentifier(String id) {
		setId(id);
	}

	public String getId() {
		return projectId;
	}

	public void setId(String id) {
		this.projectId = id.toUpperCase();
	}

	@Override
	public String toString() {
		return this.projectId;
	}
	
	

}
