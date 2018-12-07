package nz.gen.borrill.ppmtool.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import nz.gen.borrill.ppmtool.services.ProjectKeyConflictException;
import nz.gen.borrill.ppmtool.services.ProjectKeyMissingException;

@RestController
@ControllerAdvice
public class ProjectApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectKeyConflictException(ProjectKeyConflictException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectKeyMissingException(ProjectKeyMissingException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
