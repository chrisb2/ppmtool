package nz.gen.borrill.ppmtool.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.services.MapValidationErrorService;
import nz.gen.borrill.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationErrorService validationService;

	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody NewProjectDto projectDto, BindingResult result) {
		ResponseEntity<?> errors = validationService.getErrorResponse(result);
		if (errors != null) {
			return errors;
		}
		ProjectDto resultDto = new ProjectDto(this.projectService.create(projectDto.getProject()));
		return new ResponseEntity<ProjectDto>(resultDto, HttpStatus.CREATED);
	}	
	
	@PutMapping("/{projectKey}")
	public ResponseEntity<?> updateProject(@PathVariable ProjectKey projectKey, @Valid @RequestBody UpdateProjectDto projectDto, 
			BindingResult result) {
		ResponseEntity<?> errors = validationService.getErrorResponse(result);
		if (errors != null) {
			return errors;
		}
		
		Project updatedProject = projectDto.getProject(projectKey.getValue());
		ProjectDto resultDto = new ProjectDto(this.projectService.update(updatedProject));
		return new ResponseEntity<ProjectDto>(resultDto, HttpStatus.OK);
	}	
	
	@DeleteMapping("/{projectKey}")
	public ResponseEntity<?> deleteByIdentifier(@PathVariable ProjectKey projectKey) {
		projectService.deleteByKey(projectKey.getValue());
		return new ResponseEntity<String>(String.format("Project with key '%s' was deleted", projectKey), HttpStatus.OK);
	}

	@GetMapping("/{projectKey}")
	public ResponseEntity<?> getByIdentifier(@PathVariable ProjectKey projectKey) {
		Project project = projectService.findProjectByKey(projectKey.getValue());
		return new ResponseEntity<ProjectDto>(new ProjectDto(project), HttpStatus.OK);
	}
	
	@GetMapping("")
	public List<ProjectDto> getAllProjects() {
		return ProjectDto.getAllProjects(projectService.findAll());
	}
	
}
