package com.obms.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>{

	public Optional<CategoryEntity> findByCategoryName(String categoryName);

	public List<CategoryEntity> findByCategoryNameLike(String categoryNameLike);
	
}
