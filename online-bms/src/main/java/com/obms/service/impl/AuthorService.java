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
import com.obms.domain.author.AddAuthorRequest;
import com.obms.domain.author.Author;
import com.obms.domain.author.AuthorResponse;
import com.obms.domain.author.GetAuthorResponse;
import com.obms.domain.author.UpdateAuthorRequest;
import com.obms.entity.AuthorEntity;
import com.obms.repo.AuthorRepository;
import com.obms.service.IAuthorService;

@Service
public class AuthorService implements IAuthorService {

	private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AuthorResponse addAuthor(AddAuthorRequest addAuthorRequest) {
		AuthorResponse response = new AuthorResponse();

		if (Objects.isNull(addAuthorRequest) || Objects.isNull(addAuthorRequest.getAuthorName())) {

			response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
					ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "authorName",
					addAuthorRequest.getAuthorName());
			response.setSuccess(false);
			return response;

		}

		AuthorEntity authorEntity = modelMapper.map(addAuthorRequest, AuthorEntity.class);
		log.info("authorEntity : {}", authorEntity);

		try {
			AuthorEntity savedEntity = authorRepository.save(authorEntity);

			log.info("savedEntity : {}", savedEntity);

			if (Objects.nonNull(savedEntity)) {
				response = modelMapper.map(savedEntity, AuthorResponse.class);
				response.setSuccess(true);
			}

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "addAuthorRequest",
					addAuthorRequest);
		}
		return response;
	}

	/**
	 * 
	 * Given the author name only Updates the Author Description. Currently the
	 * author name update is not supported as it would necessitate the updating of
	 * all the books by that author.
	 * 
	 * 
	 */
	@Override
	public AuthorResponse updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
		AuthorResponse response = new AuthorResponse();

		System.out.println("updateAuthorRequest {}" + updateAuthorRequest);

		if (Objects.isNull(updateAuthorRequest) || Objects.isNull(updateAuthorRequest.getAuthor())
				|| Objects.isNull(updateAuthorRequest.getAuthor().getAuthorName())) {

			response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
					ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "authorName",
					updateAuthorRequest.getAuthor());
			response.setSuccess(false);
			return response;

		}

		try {

			Optional<AuthorEntity> entityToUpdateOpt = authorRepository
					.findByAuthorName(updateAuthorRequest.getAuthor().getAuthorName());

			if (entityToUpdateOpt.isPresent()) {
				AuthorEntity authorEntity = entityToUpdateOpt.get();
				authorEntity.setAuthorDescription(updateAuthorRequest.getAuthor().getAuthorDescription());
				log.info("authorEntity to update : {}", authorEntity);
				AuthorEntity savedEntity = authorRepository.save(authorEntity);
				log.info("savedEntity : {}", savedEntity);

				if (Objects.nonNull(savedEntity)) {
					response = modelMapper.map(savedEntity, AuthorResponse.class);
					response.setSuccess(true);
				}
			} else {

				log.info("The given author name does not exist.");
				response.setSuccess(false);
				response.setMessage("The given author name does not exist.");
			}
		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "updateAuthorRequest",
					updateAuthorRequest);
		}
		return response;
	}

	@Override
	public AuthorResponse deleteAuthor(List<Integer> authorIds) {

		AuthorResponse response = new AuthorResponse();
		response.setSuccess(false);
		response.setMessage(ApplicationConstants.OPERATION_NOT_SUPPORTED);

		return response;

	}

	@Override
	public GetAuthorResponse getAllAuthors() {

		GetAuthorResponse response = new GetAuthorResponse();
		response.setSuccess(false);
		response.setMessage(ApplicationConstants.OPERATION_NOT_SUPPORTED);

		return response;
	}

	@Override
	public GetAuthorResponse getAllAuthorsByNameLike(String authorNameLike) {

		GetAuthorResponse response = new GetAuthorResponse();
		if (Objects.isNull(authorNameLike) || authorNameLike.length() <= 2) {
			response.addValidationError(ErrorCodes.FIELD_LENGTH_INCORRECT.name(),
					ErrorCodes.FIELD_LENGTH_INCORRECT.getDescription(), "authorNameLike", authorNameLike);
			response.setSuccess(false);
			return response;
		}

		try {

			List<AuthorEntity> authors = authorRepository.findByAuthorNameLike("%" + authorNameLike + "%");
			
		    response.setAuthor(authors.stream().map(e -> {
				return modelMapper.map(e, Author.class);
			}).collect(Collectors.toList()));
			
		    response.setSuccess(true);

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "authorNameLike",
							authorNameLike);
		}

		return response;

	}

}
