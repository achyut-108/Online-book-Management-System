package com.obms.repo;


import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.obms.domain.customerrequest.BookDetails;
import com.obms.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, BigInteger>{
	
	@Query("select new com.obms.domain.customerrequest.BookDetails(b.id ,b.bookName,a.authorName, b.edition,"
			+ "bd.numOfCopies, bd.issuedCopies,bd.publishedDate, "
			+ "bd.price,bd.isbn, bd.availableForIssue) from BookEntity b, BookDetailEntity bd, AuthorEntity a "
			+ "where b.categoryId in :categories and b.id = bd.bookId and b.authorId = a.id")
	public List<BookDetails> findBooksByCategories(List<Integer> categories);
	
	@Query("select new com.obms.domain.customerrequest.BookDetails(b.id ,b.bookName,c.categoryName, b.edition,"
			+ "bd.numOfCopies, bd.issuedCopies,bd.publishedDate, "
			+ "bd.price,bd.isbn, bd.availableForIssue) from BookEntity b, BookDetailEntity bd, CategoryEntity c "
			+ "where b.authorId in :authors and b.id = bd.bookId and b.categoryId = c.id")
	public List<BookDetails> findBooksByAuthors(List<Integer> authors);
	
	
	@Query("select new com.obms.domain.customerrequest.BookDetails(b.id ,b.bookName, b.edition,"
			+ "bd.numOfCopies, bd.issuedCopies,bd.publishedDate, "
			+ "bd.price,bd.isbn, bd.availableForIssue, b.categoryId, b.authorId) from BookEntity b, BookDetailEntity bd "
			+ "where b.id = bd.bookId and b.id =:bookId")
	public BookDetails getBookDetails(BigInteger bookId);
}
