package nz.gen.borrill.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
			return new ResponseEntity<String>("Invalid project object", HttpStatus.BAD_REQUEST);
		}
		Project newproject = this.projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newproject, HttpStatus.CREATED);
	}
}
