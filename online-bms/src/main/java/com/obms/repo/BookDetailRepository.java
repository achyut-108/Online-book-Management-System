package com.obms.repo;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.BookDetailEntity;

@Repository
public interface BookDetailRepository extends CrudRepository<BookDetailEntity, BigInteger>{
	
	Optional<BookDetailEntity> findByBookId(BigInteger bookId);
	
}
