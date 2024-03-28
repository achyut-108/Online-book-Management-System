package com.obms.domain.customerrequest;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.obms.common.CommonServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowRequest extends CommonServiceRequest implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private BigInteger bookId;
	
}
