package com.obms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer>{
	
	
	Optional<AuthorEntity> findByAuthorName(String authorName);
	
	
	//@Query("select * from author where authorName like %?1%")
	List<AuthorEntity> findByAuthorNameLike(String authorName);
	
}
