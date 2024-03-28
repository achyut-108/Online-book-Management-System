package com.obms.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book_detail", schema = "OBMS")
public class BookDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detail_id")
	private BigInteger bookDetailId;
	@Column(name = "book_id")
	private BigInteger bookId;
	@Column(name = "num_of_copies")
	private Integer numOfCopies;
	@Column(name = "issued_copies")
	private Integer issuedCopies;
	@Column(name = "published_date")
	private Date publishedDate;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "available_for_issue")
	private String availableForIssue;
}