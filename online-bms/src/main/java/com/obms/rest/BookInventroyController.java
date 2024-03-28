package com.obms.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obms.domain.inventory.ManageInventoryRequest;
import com.obms.domain.inventory.ManageInventoryResponse;
import com.obms.domain.inventory.UpdateBookRequest;
import com.obms.service.IBookInventoryService;

@RestController
@RequestMapping("obms/inventory/manage")
public class BookInventroyController {

	private static final Logger log = LoggerFactory.getLogger(BookInventroyController.class);

	@Autowired
	private IBookInventoryService bookInventoryService;

	@PostMapping(value = "/book/add", produces = "application/json")
	public ManageInventoryResponse addBook(@RequestBody ManageInventoryRequest manageInventoryRequest) {

		log.info("request : {}", manageInventoryRequest.toString());

		return bookInventoryService.addBook(manageInventoryRequest);
	}

	
	@PostMapping(value = "/book/update", produces = "application/json")
	public ManageInventoryResponse updateBook(@RequestBody UpdateBookRequest updateBookRequest) {

		return bookInventoryService.updateBook(updateBookRequest);
	}
	
	@PostMapping(value = "/book/delete", produces = "application/json")
	public ManageInventoryResponse deleteBook(@RequestBody UpdateBookRequest updateBookRequest) {

		log.info("request : {}", updateBookRequest.toString());

		return bookInventoryService.deleteBook(updateBookRequest);
	}
}
