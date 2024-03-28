package com.obms.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.BookOrderEntity;

@Repository
public interface BookOrderRepository extends CrudRepository<BookOrderEntity, String>{
	
}
