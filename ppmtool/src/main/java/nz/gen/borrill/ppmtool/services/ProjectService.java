package nz.gen.borrill.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.domain.ProjectIdentifier;
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
	
	public Project findProjectByIdentifier(ProjectIdentifier projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifierId(projectIdentifier.getId());
		if (project==null) {
			throw new ProjectIdException(String.format("Project identifier '%s' does not exist",  projectIdentifier));
		}
		return project;
	}
	
	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}
	
	public void deleteByIdentifier(ProjectIdentifier projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifierId(projectIdentifier.getId());
		if (project==null) {
			throw new ProjectIdException(String.format("Cannot delete project with identifier '%s', it does not exist",  projectIdentifier));
		}
		projectRepository.delete(project);
	}
	
}
