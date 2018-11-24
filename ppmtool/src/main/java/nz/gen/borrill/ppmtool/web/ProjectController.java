package nz.gen.borrill.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errors = 
				result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));			
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		Project newproject = this.projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newproject, HttpStatus.CREATED);
	}
}
