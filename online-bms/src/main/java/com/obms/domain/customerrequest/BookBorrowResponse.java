package com.obms.domain.customerrequest;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.obms.common.CommonServiceResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowResponse extends CommonServiceResponse implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private String requestId;
	private String bookId;
	private String requestStatus;
	private String bookName;
	private Date issueDate;
	private Date requiredReturnDate;
	private Date requestedDate;
	private Integer numOfBooks;
	private Integer issuedCopies;
}
