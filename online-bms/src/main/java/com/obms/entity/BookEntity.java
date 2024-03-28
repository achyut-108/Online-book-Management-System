package com.obms.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book", schema = "OBMS")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigInteger id;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "author_id")
	private Integer authorId;
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "edition")
	private Integer edition;
}