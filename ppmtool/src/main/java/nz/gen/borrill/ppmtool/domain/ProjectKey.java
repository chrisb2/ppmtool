package nz.gen.borrill.ppmtool.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class ProjectKey {
	
	@NotBlank
	@Size(min=4, max=5, message="Project key must be 4 to 5 characters")
	@Column(name="projectKey", updatable=false, unique=true)	
	private String value;
	
	public ProjectKey() {}
	

	public ProjectKey(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value.toUpperCase();
	}

	@Override
	public String toString() {
		return this.value;
	}
	
}
