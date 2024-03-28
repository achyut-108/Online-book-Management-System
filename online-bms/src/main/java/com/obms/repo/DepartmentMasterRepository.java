package com.obms.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.DepartmentMasterEntity;

@Repository
public interface DepartmentMasterRepository extends CrudRepository<DepartmentMasterEntity, Integer>{
	
}
