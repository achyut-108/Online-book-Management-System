package com.obms.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book_order", schema = "OBMS")
public class BookOrderEntity {

	@Id
	@Column(name = "order_id")
	private String orderId;
	@Column(name = "book_id")
	private BigInteger bookId;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "issue_date")
	private Date issueDate;
	@Column(name = "required_return_date")
	private Date requiredReturnDate;
	@Column(name = "actual_return_date")
	private Date actualReturnDate;
	@Column(name = "num_of_copies_issued")
	private Integer numOfCopiesIssued;
	@Column(name = "request_status")
	private String requestStatus;
	@Column(name = "requested_date")
	private Date requestedDate;
}