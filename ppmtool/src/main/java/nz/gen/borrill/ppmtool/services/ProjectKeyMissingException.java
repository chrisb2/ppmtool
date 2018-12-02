package nz.gen.borrill.ppmtool.services;

public class ProjectKeyMissingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjectKeyMissingException(String projectKey) {
		super(String.format("Project with key '%s' does not exist", projectKey));
	}

}
