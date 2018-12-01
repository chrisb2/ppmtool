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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.exception.ProjectIdException;
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
	public ResponseEntity<?> createProject(@Valid @RequestBody NewProjectDto projectDto, BindingResult result) {
		ResponseEntity<?> errors = validationService.getErrorResponse(result);
		if (errors!=null) {
			return errors;
		}
		Project persistedProject = this.projectService.saveOrUpdate(projectDto.getProject());
		return new ResponseEntity<ProjectDto>(new ProjectDto(persistedProject), HttpStatus.CREATED);
	}	
	
	@PutMapping("/{projectKey}")
	public ResponseEntity<?> updateProject(@Valid @RequestBody UpdateProjectDto projectDto, @PathVariable ProjectKey projectKey, BindingResult result) {
		ResponseEntity<?> errors = validationService.getErrorResponse(result);
		if (errors!=null) {
			return errors;
		}
		
		Project existingProject = null;
		try {
			existingProject = projectService.findProjectByIdentifier(projectKey.getValue());
		} catch (ProjectIdException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		Project updatedProject = projectDto.getProject(existingProject);
		Project persistedProject = this.projectService.saveOrUpdate(updatedProject);
		return new ResponseEntity<ProjectDto>(new ProjectDto(persistedProject), HttpStatus.OK);
	}	
	
	@DeleteMapping("/{projectKey}")
	public ResponseEntity<?> deleteByIdentifier(@PathVariable ProjectKey projectKey) {
		projectService.deleteByIdentifier(projectKey.getValue());
		return new ResponseEntity<String>(String.format("Project with key '%s' was deleted", projectKey), HttpStatus.OK);
	}

	@GetMapping("/{projectKey}")
	public ResponseEntity<?> getByIdentifier(@PathVariable ProjectKey projectKey) {
		Project project = projectService.findProjectByIdentifier(projectKey.getValue());
		return new ResponseEntity<ProjectDto>(new ProjectDto(project), HttpStatus.OK);
	}
	
	@GetMapping("")
	public Iterable<Project> getAllProjects() {
		return projectService.findAll();
	}
	
}
