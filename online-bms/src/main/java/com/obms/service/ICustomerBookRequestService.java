package com.obms.service;

import com.obms.domain.customerrequest.BookBorrowRequest;
import com.obms.domain.customerrequest.BookBorrowResponse;
import com.obms.domain.customerrequest.BrowseBookRequest;
import com.obms.domain.customerrequest.BrowseBookResponse;

public interface ICustomerBookRequestService {

	public BrowseBookResponse searchBookByCategory(BrowseBookRequest browseBookRequest);
	public BrowseBookResponse searchBookByAuthor(BrowseBookRequest browseBookRequest);
	public BookBorrowResponse bookBorrowRequest(BookBorrowRequest bookBorrowRequest);
	
}
