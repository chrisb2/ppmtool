package nz.gen.borrill.ppmtool.services;

public class ProjectIdConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjectIdConflictException(String message, Exception ex) {
		super(message, ex);
	}
	

}
