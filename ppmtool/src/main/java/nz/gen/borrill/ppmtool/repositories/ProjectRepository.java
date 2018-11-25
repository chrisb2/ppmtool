package nz.gen.borrill.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nz.gen.borrill.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectIdentifierId(String projectIdentifier);

	@Override
	Iterable<Project> findAll();
	
}
