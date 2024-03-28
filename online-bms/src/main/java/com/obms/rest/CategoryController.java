package com.obms.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obms.domain.category.AddCategoryRequest;
import com.obms.domain.category.CategoryResponse;
import com.obms.domain.category.GetCategoryResponse;
import com.obms.domain.category.UpdateCategoryRequest;
import com.obms.service.ICategoryService;

@RestController
@RequestMapping("obms/manage/category")
public class CategoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping(value="/add",produces="application/json")
	public CategoryResponse addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
		
		log.debug("request : {}", addCategoryRequest.toString());
		return categoryService.addCategory(addCategoryRequest);
					
	}

	@PatchMapping(value = "/update", produces = "application/json")
	public CategoryResponse updateCategory(@RequestBody @Validated UpdateCategoryRequest updateCategoryRequest) {
		log.debug("request : {}", updateCategoryRequest.toString());

		return categoryService.updateCategory(updateCategoryRequest);
	}

	@PutMapping(value = "/delete", produces = "application/json")
	public CategoryResponse deleteCategory(@RequestBody List<Integer> categoryIds) {

		categoryIds.forEach(e -> log.debug(" {}", e));

		return categoryService.deleteCategory(categoryIds);
	}
	
	@PostMapping(value="/findByNameLike", produces="application/json")
	public GetCategoryResponse findCategoriesByName(@RequestBody String categoryName) {
		log.info("request : {}", categoryName);
		return categoryService.getAllCategoriesByNameLike(categoryName);
					
	}

	
}
