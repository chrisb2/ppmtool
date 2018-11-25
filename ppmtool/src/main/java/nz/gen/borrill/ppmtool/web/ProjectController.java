package nz.gen.borrill.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.domain.ProjectIdentifier;
import nz.gen.borrill.ppmtool.services.MapValidationErrorService;
import nz.gen.borrill.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationErrorService validationService;

	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errors = validationService.getErrorResponse(result);
		if (errors!=null) {
			return errors;
		}
		Project newproject = this.projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newproject, HttpStatus.CREATED);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdentifier(@PathVariable ProjectIdentifier id) {
		Project project = projectService.findProjectByIdentifier(id);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("")
	public Iterable<Project> getAllProjects() {
		return projectService.findAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteByIdentifier(@PathVariable ProjectIdentifier id) {
		projectService.deleteByIdentifier(id);
		return new ResponseEntity<String>(String.format("Project with identifier '%s' was deleted", id), HttpStatus.OK);
	}

}
