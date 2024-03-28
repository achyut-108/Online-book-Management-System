package com.obms.domain.customerrequest;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.obms.common.CommonServiceRequest;
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
public class BrowseBookResponse extends CommonServiceResponse implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private List<BookDetails> books;
	
}
