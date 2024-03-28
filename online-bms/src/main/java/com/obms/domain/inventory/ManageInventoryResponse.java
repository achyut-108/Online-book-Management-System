package com.obms.domain.inventory;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.obms.common.CommonServiceResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonAutoDetect
@EqualsAndHashCode(callSuper = false)
public class ManageInventoryResponse extends CommonServiceResponse implements Serializable {
	public static final long serialVersionUID = 1L;
	
	private BigInteger bookId;
}