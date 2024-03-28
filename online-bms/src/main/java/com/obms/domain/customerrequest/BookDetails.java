package com.obms.domain.customerrequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonAutoDetect
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties
public class BookDetails {

	private BigInteger bookId;
	private String bookName;
	private String authorName;
	private String categoryName;
	private Integer edition;
	private Integer numOfCopies;
	private Integer issuedCopies;
	private Date publishedDate;
	private BigDecimal price;
	private String isbn;
	private String availableForIssue;
	private Integer authorId;
	private Integer categoryId;

	public BookDetails() {
	}

	public BookDetails(BigInteger bookId, String bookName, String authorName, Integer edition, Integer numOfCopies,
			Integer issuedCopies, Date publishedDate, BigDecimal price, String isbn, String availableForIssue) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.edition = edition;
		this.numOfCopies = numOfCopies;
		this.issuedCopies = issuedCopies;
		this.publishedDate = publishedDate;
		this.price = price;
		this.isbn = isbn;
		this.availableForIssue = availableForIssue;
	}

	public BookDetails(BigInteger bookId, String bookName, Integer edition, String categoryName, Integer numOfCopies,
			Integer issuedCopies, Date publishedDate, BigDecimal price, String isbn, String availableForIssue) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.categoryName = categoryName;
		this.edition = edition;
		this.numOfCopies = numOfCopies;
		this.issuedCopies = issuedCopies;
		this.publishedDate = publishedDate;
		this.price = price;
		this.isbn = isbn;
		this.availableForIssue = availableForIssue;
	}

	public BookDetails(BigInteger bookId, String bookName, Integer edition, Integer numOfCopies, Integer issuedCopies,
			Date publishedDate, BigDecimal price, String isbn, String availableForIssue, Integer authorId,
			Integer categoryId) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.edition = edition;
		this.numOfCopies = numOfCopies;
		this.issuedCopies = issuedCopies;
		this.publishedDate = publishedDate;
		this.price = price;
		this.isbn = isbn;
		this.availableForIssue = availableForIssue;
		this.authorId = authorId;
		this.categoryId = categoryId;
	}

}
