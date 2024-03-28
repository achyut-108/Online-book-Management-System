package com.obms.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.ProjectMasterEntity;

@Repository
public interface ProjectMasterRepository extends CrudRepository<ProjectMasterEntity, Integer>{
	
}
