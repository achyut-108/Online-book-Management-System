package com.obms.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.RoleMasterEntity;

@Repository
public interface RoleMasterRepository extends CrudRepository<RoleMasterEntity, Integer>{
	
}
