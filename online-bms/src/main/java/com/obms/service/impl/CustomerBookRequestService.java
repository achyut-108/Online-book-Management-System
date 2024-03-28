package com.obms.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obms.common.ApplicationConstants;
import com.obms.common.ErrorCodes;
import com.obms.domain.author.GetAuthorResponse;
import com.obms.domain.category.GetCategoryResponse;
import com.obms.domain.customerrequest.BookBorrowRequest;
import com.obms.domain.customerrequest.BookBorrowResponse;
import com.obms.domain.customerrequest.BookDetails;
import com.obms.domain.customerrequest.BrowseBookRequest;
import com.obms.domain.customerrequest.BrowseBookResponse;
import com.obms.entity.BookDetailEntity;
import com.obms.entity.BookEntity;
import com.obms.entity.BookOrderEntity;
import com.obms.repo.BookDetailRepository;
import com.obms.repo.BookOrderRepository;
import com.obms.repo.BookRepository;
import com.obms.service.IAuthorService;
import com.obms.service.ICategoryService;
import com.obms.service.ICustomerBookRequestService;

@Service
public class CustomerBookRequestService implements ICustomerBookRequestService {

	private static final Logger log = LoggerFactory.getLogger(CustomerBookRequestService.class);

	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookOrderRepository bookOrderRepository;
	@Autowired
	private BookDetailRepository bookDetailRepository;

	@Override
	public BrowseBookResponse searchBookByCategory(BrowseBookRequest browseBookRequest) {

		BrowseBookResponse response = new BrowseBookResponse();

		try {

			if (Objects.isNull(browseBookRequest) || Objects.isNull(browseBookRequest.getBookCategory())) {

				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "bookCategory", browseBookRequest);
				response.setSuccess(false);
				return response;

			}

			GetCategoryResponse categories = categoryService
					.getAllCategoriesByNameLike(browseBookRequest.getBookCategory());

			categories.getCategory().forEach(System.out::println);

			List<Integer> categoryIds = categories.getCategory().stream().map(e -> {
				return e.getCategoryId();
			}).collect(Collectors.toList());

			List<BookDetails> bookDetailsList = bookRepository.findBooksByCategories(categoryIds);

			bookDetailsList.forEach(System.out::println);

			response.setBooks(bookDetailsList);
			response.setSuccess(true);

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "browseBookRequest",
					browseBookRequest);
		}

		return response;
	}

	@Override
	public BrowseBookResponse searchBookByAuthor(BrowseBookRequest browseBookRequest) {

		BrowseBookResponse response = new BrowseBookResponse();

		try {

			if (Objects.isNull(browseBookRequest) || Objects.isNull(browseBookRequest.getAuthorName())) {

				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "authorName", browseBookRequest);
				response.setSuccess(false);
				return response;

			}

			GetAuthorResponse authors = authorService.getAllAuthorsByNameLike(browseBookRequest.getAuthorName());

			authors.getAuthor().forEach(System.out::println);

			List<Integer> authorIds = authors.getAuthor().stream().map(e -> {
				return e.getAuthorId();
			}).collect(Collectors.toList());

			List<BookDetails> bookDetailsList = bookRepository.findBooksByAuthors(authorIds);

			bookDetailsList.forEach(System.out::println);

			response.setBooks(bookDetailsList);
			response.setSuccess(true);

		} catch (Exception e) {
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "browseBookRequest",
					browseBookRequest);
		}

		return response;
	}

	@Override
	@Transactional
	public BookBorrowResponse bookBorrowRequest(BookBorrowRequest bookBorrowRequest) {
		BookBorrowResponse response = new BookBorrowResponse();

		try {

			if (Objects.isNull(bookBorrowRequest) || Objects.isNull(bookBorrowRequest.getBookId())
					|| Objects.isNull(bookBorrowRequest.getUserId())) {

				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "bookBorrowRequest", bookBorrowRequest);
				response.setSuccess(false);
				return response;
			}

			BookOrderEntity bookOrderEntity = new BookOrderEntity();

			BookDetails bookDetails = bookRepository.getBookDetails(bookBorrowRequest.getBookId());

			if (Objects.isNull(bookDetails)) {
				response.setSuccess(false);
				response.addValidationError(ErrorCodes.MANDATORY_FIELDS_MISSING.name(),
						ErrorCodes.MANDATORY_FIELDS_MISSING.getDescription(), "bookId", bookBorrowRequest.getBookId());
				return response;
			}

			Integer numOfBooksAvailableForIssue = bookDetails.getNumOfCopies()
					- (Objects.nonNull(bookDetails.getIssuedCopies()) ? bookDetails.getIssuedCopies() : 0);

			if (bookDetails.getAvailableForIssue().equalsIgnoreCase(ApplicationConstants.AVAILABLE_FOR_ISSUE)
					&& numOfBooksAvailableForIssue > 0) {
				String orderId = new StringBuilder(String.valueOf(bookDetails.getBookId()))
						.append(bookBorrowRequest.getUserId()).append(LocalDateTime.now())
						.append(bookDetails.getAuthorId()).toString();

				bookOrderEntity.setOrderId(orderId);
				bookOrderEntity.setCustomerId(bookBorrowRequest.getUserId());
				bookOrderEntity.setRequestStatus(ApplicationConstants.PENDING);
				bookOrderEntity.setRequestedDate(new Date());
				bookOrderEntity.setBookId(bookDetails.getBookId());
				bookOrderEntity.setIssueDate(new Date());
				bookOrderEntity.setNumOfCopiesIssued(1);
				bookOrderEntity.setRequestStatus(ApplicationConstants.ISSUED);
				Date requiredReturnDate = Date
						.from(LocalDateTime.now().plusDays(ApplicationConstants.MAX_DAYS_FOR_BORROWING_BOOK)
								.atZone(ZoneId.systemDefault()).toInstant());
				;
				bookOrderEntity.setRequiredReturnDate(requiredReturnDate);

				bookOrderEntity = bookOrderRepository.save(bookOrderEntity);

				BookDetailEntity bookDetailEntity = bookDetailRepository.findByBookId(bookDetails.getBookId()).get();

				bookDetailEntity.setIssuedCopies(
						(Objects.nonNull(bookDetails.getIssuedCopies()) ? bookDetails.getIssuedCopies() : 0) + 1);

				bookDetailRepository.save(bookDetailEntity);

				response.setSuccess(true);
				response.setBookId(bookOrderEntity.getOrderId());
				response.setBookName(bookDetails.getBookName());
				response.setRequestStatus(bookOrderEntity.getRequestStatus());
				response.setRequestedDate(bookOrderEntity.getRequestedDate());
				response.setRequestId(bookOrderEntity.getOrderId());
				response.setRequiredReturnDate(requiredReturnDate);
				response.setNumOfBooks(bookDetails.getNumOfCopies());
				response.setIssuedCopies(bookDetailEntity.getIssuedCopies());

			} else {

				response.setSuccess(false);
				response.setRequestStatus(ApplicationConstants.FAILED);
				response.setNumOfBooks(bookDetails.getNumOfCopies());
				response.setIssuedCopies(bookDetails.getIssuedCopies());
				response.setMessage(ApplicationConstants.BOOK_NOT_AVAILABLE_FOR_ISSUE);
			}

			return response;

		} catch (Exception e) {
			log.error("Exception : {}", e);
			response.setSuccess(false);
			response.addValidationError(ErrorCodes.TECHNICAL_ERROR.name(),
					e != null ? e.getMessage() : ErrorCodes.TECHNICAL_ERROR.getDescription(), "bookBorrowRequest",
					bookBorrowRequest);
		}

		return null;
	}

}
