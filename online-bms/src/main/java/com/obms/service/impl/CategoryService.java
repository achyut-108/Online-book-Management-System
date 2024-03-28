package com.obms.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obms.common.ApplicationConstants;
import com.obms.common.ErrorCodes;
import com.obms.domain.category.AddCategoryRequest;
import com.obms.domain.category.Category;
import com.obms.domain.category.CategoryResponse;
import com.obms.domain.category.GetCategoryResponse;
import com.obms.domain.category.UpdateCategoryRequest;
import com.obms.entity.CategoryEntity;
import com.obms.repo.CategoryRepository;
import com.obms.service.ICategoryService;


@Service
public class CategoryService implements ICategoryService{
	
	private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
		CategoryResponse response = new CategoryResponse();

		if (Objects.isNull(addCategoryRequest) || Objects.isNull(addCategoryRequest.getCategoryName()) 
				|| Objects.isNull(addCategoryRequest.getCategoryDescription())) {

			response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
					ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "categoryName, categoryDescription",
					addCategoryRequest);
			response.setSuccess(false);
			return response;

		}

		CategoryEntity categoryEntity = modelMapper.map(addCategoryRequest, CategoryEntity.class);
		log.info("authorEntity : {}", categoryEntity);

		try {
			CategoryEntity savedEntity = categoryRepository.save(categoryEntity);

			log.info("savedEntity : {}", savedEntity);

			if (Objects.nonNull(savedEntity)) {
				response = modelMapper.map(savedEntity, CategoryResponse.class);
				response.setSuccess(true);
			}

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "addCategoryRequest",
							addCategoryRequest);
		}
		return response;

	}

	@Override
	public CategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest) {
		CategoryResponse response = new CategoryResponse();

		log.info("updateCategoryRequest {}" + updateCategoryRequest);

		if (Objects.isNull(updateCategoryRequest) || Objects.isNull(updateCategoryRequest.getCategory())
				|| Objects.isNull(updateCategoryRequest.getCategory().getCategoryDescription())) {

			response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
					ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "categoryName",
					updateCategoryRequest.getCategory());
			response.setSuccess(false);
			return response;

		}

		try {

			Optional<CategoryEntity> entityToUpdateOpt = categoryRepository
					.findByCategoryName(updateCategoryRequest.getCategory().getCategoryName());

			if (entityToUpdateOpt.isPresent()) {
				CategoryEntity categoryEntity = entityToUpdateOpt.get();
				categoryEntity.setCategoryDescription(updateCategoryRequest.getCategory().getCategoryDescription());
				log.info("authorEntity to update : {}", categoryEntity);
				CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
				log.info("savedEntity : {}", savedEntity);

				if (Objects.nonNull(savedEntity)) {
					response = modelMapper.map(savedEntity, CategoryResponse.class);
					response.setSuccess(true);
				}
			} else {

				log.info("The given Category name does not exist.");
				response.setSuccess(false);
				response.setMessage("The given Category name does not exist.");
			}
		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "updateCategoryRequest",
					updateCategoryRequest);
		}
		return response;
	}

	@Override
	public CategoryResponse deleteCategory(List<Integer> categoryIds) {
		CategoryResponse response = new CategoryResponse();
		response.setSuccess(false);
		response.setMessage(ApplicationConstants.OPERATION_NOT_SUPPORTED);

		return response;
	}

	@Override
	public GetCategoryResponse getAllCategories() {
		GetCategoryResponse response = new GetCategoryResponse();
		response.setSuccess(false);
		response.setMessage(ApplicationConstants.OPERATION_NOT_SUPPORTED);

		return response;
	}

	@Override
	public GetCategoryResponse getAllCategoriesByNameLike(String categoryNameLike) {
		GetCategoryResponse response = new GetCategoryResponse();
		if (Objects.isNull(categoryNameLike) || categoryNameLike.length() <= 2) {
			response.addValidationError(ErrorCodes.FIELD_LENGTH_INCORRECT.name(),
					ErrorCodes.FIELD_LENGTH_INCORRECT.getDescription(), "categoryNameLike", categoryNameLike);
			response.setSuccess(false);
			return response;
		}

		try {

			List<CategoryEntity> authors = categoryRepository.findByCategoryNameLike("%" + categoryNameLike + "%");
			
		    response.setCategory(authors.stream().map(e -> {
				return modelMapper.map(e, Category.class);
			}).collect(Collectors.toList()));
			
		    response.setSuccess(true);

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "categoryNameLike",
							categoryNameLike);
		}

		return response;

	}

}
