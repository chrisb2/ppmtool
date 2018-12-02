package nz.gen.borrill.ppmtool.services;

public class ProjectKeyConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjectKeyConflictException(String projectKey, Exception ex) {
		super(String.format("Project with key '%s' already exists", projectKey) ,ex);
	}
	

}
