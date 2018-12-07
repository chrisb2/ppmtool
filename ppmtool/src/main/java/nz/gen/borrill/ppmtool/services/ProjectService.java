package nz.gen.borrill.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.repositories.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project create(Project project) {
		try {
			return projectRepository.save(project);
		} catch (DataIntegrityViolationException ex) {
			throw new ProjectKeyConflictException(project.getProjectKey(), ex);
		}
	}
	
	public Project update(Project project) {
		Project existing = findProjectByKey(project.getProjectKey());
		project.updateFromExisting(existing);
		return projectRepository.save(project);			
	}
	
	public Project findProjectByKey(String projectKey) {
		Project project = projectRepository.findByProjectKey(projectKey);
		if (project == null) {
			throw new ProjectKeyMissingException(projectKey);			
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
