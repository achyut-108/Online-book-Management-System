package com.obms.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obms.common.ErrorCodes;
import com.obms.domain.inventory.ManageInventoryRequest;
import com.obms.domain.inventory.ManageInventoryResponse;
import com.obms.domain.inventory.UpdateBookRequest;
import com.obms.entity.AuthorEntity;
import com.obms.entity.BookDetailEntity;
import com.obms.entity.BookEntity;
import com.obms.entity.CategoryEntity;
import com.obms.repo.AuthorRepository;
import com.obms.repo.BookDetailRepository;
import com.obms.repo.BookRepository;
import com.obms.repo.CategoryRepository;
import com.obms.service.IBookInventoryService;

@Service
public class BookInventoryService implements IBookInventoryService {
	
	private static final Logger log = LoggerFactory.getLogger(BookInventoryService.class);

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookDetailRepository bookDetailRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public ManageInventoryResponse addBook(ManageInventoryRequest manageInventoryRequest) {

		ManageInventoryResponse response = new ManageInventoryResponse();

		try {

			if (Objects.isNull(manageInventoryRequest) || !isValidAddBookDetails(manageInventoryRequest)) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "manageInventoryRequest",
						manageInventoryRequest);
				return response;
			}

			Optional<AuthorEntity> authorEntityOpt = authorRepository.findById(manageInventoryRequest.getAuthorId());

			if (!authorEntityOpt.isPresent()) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.INVALID_DATA.name(), ErrorCodes.INVALID_DATA.getDescription(),
						"authorId", manageInventoryRequest.getAuthorId());
				return response;
			}

			Optional<CategoryEntity> categoryEntityOpt = categoryRepository
					.findById(manageInventoryRequest.getCategoryId());

			if (!categoryEntityOpt.isPresent()) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.INVALID_DATA.name(), ErrorCodes.INVALID_DATA.getDescription(),
						"categoryId", manageInventoryRequest.getCategoryId());
				return response;
			}

			BookEntity bookEntity = modelMapper.map(manageInventoryRequest, BookEntity.class);
			bookEntity = bookRepository.save(bookEntity);
			BookDetailEntity bookDetailEntity = modelMapper.map(manageInventoryRequest, BookDetailEntity.class);

			bookDetailEntity.setBookId(bookEntity.getId());
			bookDetailEntity = bookDetailRepository.save(bookDetailEntity);

			response.setSuccess(true);
			response.setBookId(bookEntity.getId());

		} catch (Exception e) {

			e.printStackTrace();
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "manageInventoryRequest",
					manageInventoryRequest);
		}

		return response;
	}

	private boolean isValidAddBookDetails(ManageInventoryRequest manageInventoryRequest) {

		return Objects.nonNull(manageInventoryRequest.getAuthorId())
				&& Objects.nonNull(manageInventoryRequest.getBookName())
				&& Objects.nonNull(manageInventoryRequest.getIsbn())
				&& Objects.nonNull(manageInventoryRequest.getNumOfCopies())
				&& Objects.nonNull(manageInventoryRequest.getPrice())
				&& Objects.nonNull(manageInventoryRequest.getCategoryId())
				&& Objects.nonNull(manageInventoryRequest.getEdition())
				&& Objects.nonNull(manageInventoryRequest.getPublishedDate())
				&& Objects.nonNull(manageInventoryRequest.getAvailableForIssue());
	}

	@Transactional
	@Override
	public ManageInventoryResponse updateBook(UpdateBookRequest updateBookRequest) {

		ManageInventoryResponse response = new ManageInventoryResponse();

		try {

			if (Objects.isNull(updateBookRequest) || Objects.isNull(updateBookRequest.getBookId())) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "updateBookRequest", updateBookRequest);
				return response;
			}

			Optional<BookEntity> bookEntityOpt = bookRepository.findById(updateBookRequest.getBookId());
			

			if (!bookEntityOpt.isPresent()) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "bookId", updateBookRequest.getBookId());
				return response;
			}

			Optional<BookDetailEntity> bookDetailEntityOpt = bookDetailRepository.findByBookId(updateBookRequest.getBookId());
		
			Optional<AuthorEntity> authorEntityOpt = null;
			if (Objects.nonNull(updateBookRequest.getAuthorId())) {
				authorEntityOpt = authorRepository.findById(updateBookRequest.getAuthorId());

				if (!authorEntityOpt.isPresent()) {
					response.setSuccess(false);
					response.addValidationError(ErrorCodes.INVALID_DATA.name(),
							ErrorCodes.INVALID_DATA.getDescription(), "authorId", updateBookRequest.getAuthorId());
					return response;
				}

			}

			Optional<CategoryEntity> categoryEntityOpt = null;
			if (Objects.nonNull(updateBookRequest.getCategoryId())) {
				categoryEntityOpt = categoryRepository.findById(updateBookRequest.getCategoryId());

				if (!categoryEntityOpt.isPresent()) {
					response.setSuccess(false);
					response.addValidationError(ErrorCodes.INVALID_DATA.name(),
							ErrorCodes.INVALID_DATA.getDescription(), "categoryId", updateBookRequest.getCategoryId());
					return response;
				}

			}
			
			updateFieldsForBookUpdate(updateBookRequest,bookEntityOpt.get(), bookDetailEntityOpt.get());

			BookEntity bookEntity = bookRepository.save(bookEntityOpt.get());

			BookDetailEntity bookDetailEntity = bookDetailEntityOpt.get();
			bookDetailEntity.setBookId(bookEntity.getId());
			bookDetailEntity = bookDetailRepository.save(bookDetailEntityOpt.get());

			response.setSuccess(true);
			response.setBookId(bookEntity.getId());

		} catch (Exception e) {

			e.printStackTrace();
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "updateBookRequest",
					updateBookRequest);
		}

		return response;
	}

	private void updateFieldsForBookUpdate(UpdateBookRequest updateBookRequest, BookEntity bookEntity,
			BookDetailEntity bookDetailEntity) {

		bookEntity.setAuthorId(Objects.nonNull(updateBookRequest.getAuthorId()) ? updateBookRequest.getAuthorId()
				: bookEntity.getAuthorId());
		bookEntity.setCategoryId(Objects.nonNull(updateBookRequest.getCategoryId()) ? updateBookRequest.getCategoryId()
				: bookEntity.getCategoryId());
		bookEntity.setEdition(Objects.nonNull(updateBookRequest.getEdition()) ? updateBookRequest.getEdition()
				: bookEntity.getEdition());
		bookEntity.setBookName(Objects.nonNull(updateBookRequest.getBookName()) ? updateBookRequest.getBookName()
				: bookEntity.getBookName());

		bookDetailEntity.setIsbn(Objects.nonNull(updateBookRequest.getIsbn()) ? updateBookRequest.getIsbn()
				: bookDetailEntity.getIsbn());
		bookDetailEntity
				.setNumOfCopies(Objects.nonNull(updateBookRequest.getNumOfCopies()) ? updateBookRequest.getNumOfCopies()
						: bookDetailEntity.getNumOfCopies());
		bookDetailEntity.setPrice(Objects.nonNull(updateBookRequest.getPrice()) ? updateBookRequest.getPrice()
				: bookDetailEntity.getPrice());
		bookDetailEntity.setPublishedDate(
				Objects.nonNull(updateBookRequest.getPublishedDate()) ? updateBookRequest.getPublishedDate()
						: bookDetailEntity.getPublishedDate());
		bookDetailEntity.setAvailableForIssue(Objects.nonNull(updateBookRequest.getAvailableForIssue()) ? 
				updateBookRequest.getAvailableForIssue() : bookDetailEntity.getAvailableForIssue());

	}

	@Transactional
	@Override
	public ManageInventoryResponse deleteBook(UpdateBookRequest updateBookRequest) {

		ManageInventoryResponse response = new ManageInventoryResponse();

		try {

			if (Objects.isNull(updateBookRequest) || Objects.isNull(updateBookRequest.getBookId())) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "updateBookRequest", updateBookRequest);
				return response;
			}

			Optional<BookEntity> bookEntityOpt = bookRepository.findById(updateBookRequest.getBookId());
			

			if (!bookEntityOpt.isPresent()) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "bookId", updateBookRequest.getBookId());
				return response;
			}

			Optional<BookDetailEntity> bookDetailEntityOpt = bookDetailRepository.findByBookId(updateBookRequest.getBookId());

			bookRepository.delete(bookEntityOpt.get());

			BookDetailEntity bookDetailEntity = bookDetailEntityOpt.get();
			bookDetailEntity.setBookId(bookEntityOpt.get().getId());
			bookDetailRepository.delete(bookDetailEntity);

			response.setSuccess(true);
			response.setBookId(bookEntityOpt.get().getId());

		} catch (Exception e) {

			e.printStackTrace();
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "updateBookRequest",
					updateBookRequest);
		}

		return response;
	}

	
	
}
