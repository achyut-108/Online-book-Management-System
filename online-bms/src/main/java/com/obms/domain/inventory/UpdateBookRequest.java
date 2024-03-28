package com.obms.domain.inventory;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest extends ManageInventoryRequest implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private BigInteger bookId;
	
}
