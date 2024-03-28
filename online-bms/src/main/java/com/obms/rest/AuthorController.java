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

import com.obms.domain.author.AddAuthorRequest;
import com.obms.domain.author.AuthorResponse;
import com.obms.domain.author.GetAuthorResponse;
import com.obms.domain.author.UpdateAuthorRequest;
import com.obms.service.IAuthorService;

@RestController
@RequestMapping("obms/manage/author")
public class AuthorController {

	private static final Logger log = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private IAuthorService authorService;
	
	@PostMapping(value="/add",produces="application/json")
	public AuthorResponse addAuthor(@RequestBody AddAuthorRequest addAuthorRequest) {
		
		log.info("request : {}", addAuthorRequest.toString());
		return authorService.addAuthor(addAuthorRequest);
					
	}

	@PatchMapping(value = "/update", produces = "application/json")
	public AuthorResponse updateAuthor(@RequestBody @Validated UpdateAuthorRequest updateAuthorRequest) {
		log.info("request : {}", updateAuthorRequest.toString());

		return authorService.updateAuthor(updateAuthorRequest);
	}

	@PutMapping(value = "/delete", produces = "application/json")
	public AuthorResponse deleteAuthor(@RequestBody List<Integer> authorIds) {

		authorIds.forEach(System.out::println);

		return authorService.deleteAuthor(authorIds);
	}
	
	@PostMapping(value="/findByNameLike",produces="application/json")
	public GetAuthorResponse findAuthorsByName(@RequestBody String authorName) {
		log.info("request : {}", authorName);
		return authorService.getAllAuthorsByNameLike(authorName);
					
	}

	
}
