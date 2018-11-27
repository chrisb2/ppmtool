package nz.gen.borrill.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.domain.ProjectKey;
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
			throw new ProjectIdException(String.format("Project key '%s' already exists", project.getProjectKey()));
		}
	}
	
	public Project findProjectByIdentifier(ProjectKey projectKey) {
		Project project = projectRepository.findByProjectKeyValue(projectKey.getValue());
		if (project==null) {
			throw new ProjectIdException(String.format("Project key '%s' does not exist",  projectKey));
		}
		return project;
	}
	
	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}
	
	public void deleteByIdentifier(ProjectKey projectKey) {
		Project project = projectRepository.findByProjectKeyValue(projectKey.getValue());
		if (project==null) {
			throw new ProjectIdException(String.format("Cannot delete project with key '%s', it does not exist",  projectKey));
		}
		projectRepository.delete(project);
	}
	
}
