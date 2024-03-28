package com.obms.repo;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.BookOrderEntity;

@Repository
public interface UserMasterRepository extends CrudRepository<BookOrderEntity, BigInteger>{
	
}
