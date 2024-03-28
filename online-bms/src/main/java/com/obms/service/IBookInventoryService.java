package com.obms.service;

import com.obms.domain.inventory.ManageInventoryRequest;
import com.obms.domain.inventory.ManageInventoryResponse;
import com.obms.domain.inventory.UpdateBookRequest;

public interface IBookInventoryService {

	public ManageInventoryResponse addBook(ManageInventoryRequest manageInventoryRequest);
	public ManageInventoryResponse updateBook(UpdateBookRequest updateBookRequest);
	public ManageInventoryResponse deleteBook(UpdateBookRequest updateBookRequest);
}
