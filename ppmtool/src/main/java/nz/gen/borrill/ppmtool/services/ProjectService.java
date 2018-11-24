package nz.gen.borrill.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.gen.borrill.ppmtool.domain.Project;
import nz.gen.borrill.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		return projectRepository.save(project);
	}
}
