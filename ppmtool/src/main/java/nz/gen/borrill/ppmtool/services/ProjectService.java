package nz.gen.borrill.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.exception.ProjectIdException;
import nz.gen.borrill.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception ex) {
			throw new ProjectIdException(String.format("Project identifier '%s' already exists", project.getProjectIdentifier()));
		}
	}
	
	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if (project==null) {
			throw new ProjectIdException(String.format("Project identifier '%s' does not exist",  projectIdentifier.toUpperCase()));
		}
		return project;
	}
	
	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}
}
