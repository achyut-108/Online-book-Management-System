package com.obms.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.ProfileMasterEntity;

@Repository
public interface ProfileMasterRepository extends CrudRepository<ProfileMasterEntity, Integer>{
	
}
