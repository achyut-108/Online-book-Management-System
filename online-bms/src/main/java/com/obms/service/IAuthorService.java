package com.obms.service;

import java.util.List;

import com.obms.domain.author.AddAuthorRequest;
import com.obms.domain.author.AuthorResponse;
import com.obms.domain.author.GetAuthorResponse;
import com.obms.domain.author.UpdateAuthorRequest;

public interface IAuthorService {

	public AuthorResponse addAuthor(AddAuthorRequest addAuthorRequest);
	public AuthorResponse updateAuthor(UpdateAuthorRequest updateAuthorRequest);
	public AuthorResponse deleteAuthor(List<Integer> authorIds);
	public GetAuthorResponse getAllAuthors();
	public GetAuthorResponse getAllAuthorsByNameLike(String authorNameLike);
}
