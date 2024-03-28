package com.obms.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obms.domain.customerrequest.BookBorrowRequest;
import com.obms.domain.customerrequest.BookBorrowResponse;
import com.obms.domain.customerrequest.BrowseBookRequest;
import com.obms.domain.customerrequest.BrowseBookResponse;
import com.obms.service.ICustomerBookRequestService;

@RestController
@RequestMapping("obms/book/")
public class CustomerBookRequestController {

	private static final Logger log = LoggerFactory.getLogger(CustomerBookRequestController.class);

	@Autowired
	private ICustomerBookRequestService customerBookRequestService;

	@PostMapping(value = "/searchbycategory", produces = "application/json")
	public BrowseBookResponse searchBooksByCategory(@RequestBody BrowseBookRequest browseBookRequest) {

		log.debug("request : {}", browseBookRequest.toString());

		return customerBookRequestService.searchBookByCategory(browseBookRequest);
	}
	
	@PostMapping(value = "/searchbyauthor", produces = "application/json")
	public BrowseBookResponse searchBooksByAuthor(@RequestBody BrowseBookRequest browseBookRequest) {

		log.debug("request : {}", browseBookRequest.toString());

		return customerBookRequestService.searchBookByAuthor(browseBookRequest);
	}
	
	@PostMapping(value = "/request/borrow", produces = "application/json")
	public BookBorrowResponse bookBorrowRequest(@RequestBody BookBorrowRequest bookBorrowRequest) {

		log.debug("request : {}", bookBorrowRequest.toString());

		return customerBookRequestService.bookBorrowRequest(bookBorrowRequest);
	}
}
