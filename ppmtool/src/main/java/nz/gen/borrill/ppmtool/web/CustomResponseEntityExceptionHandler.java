package nz.gen.borrill.ppmtool.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import nz.gen.borrill.ppmtool.services.ProjectIdConflictException;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdConflictException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}