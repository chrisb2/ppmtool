package nz.gen.borrill.ppmtool.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.exception.ProjectIdException;
import nz.gen.borrill.ppmtool.repositories.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project create(Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception ex) {
			throw new ProjectIdException(String.format("Project key '%s' already exists", project.getProjectKey()));
		}
	}
	
	public Project update(Project project) {
		Project existing = findProjectByKey(project.getProjectKey());
		project.setId(existing.getId());
		project.setCreatedAt(existing.getCreatedAt());
		return projectRepository.save(project);
	}
	
	public Project findProjectByKey(String projectKey) {
		Project project = projectRepository.findByProjectKey(projectKey);
		if (project==null) {
			throw new ProjectIdException(String.format("Project key '%s' does not exist",  projectKey));
		}
		return project;
	}
	
	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}
	
	public void deleteByKey(String projectKey) {
		Project project = projectRepository.findByProjectKey(projectKey);
		if (project != null) {
			projectRepository.delete(project);
		}
	}
	
}
