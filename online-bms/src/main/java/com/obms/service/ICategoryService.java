package com.obms.service;

import java.util.List;

import com.obms.domain.category.AddCategoryRequest;
import com.obms.domain.category.CategoryResponse;
import com.obms.domain.category.GetCategoryResponse;
import com.obms.domain.category.UpdateCategoryRequest;

public interface ICategoryService {

	public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
	public CategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest);
	public CategoryResponse deleteCategory(List<Integer> categoryIds);
	public GetCategoryResponse getAllCategories();
	public GetCategoryResponse getAllCategoriesByNameLike(String CategoryNameLike);
	
}
