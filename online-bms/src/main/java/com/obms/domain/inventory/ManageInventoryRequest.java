package com.obms.domain.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class ManageInventoryRequest extends CommonServiceRequest implements Serializable{
	public static final long serialVersionUID = 1L;
	
	
	private String bookName;
	private Integer authorId;
	private Integer categoryId;
	private Integer edition;
	private Integer numOfCopies;
	private Date publishedDate;
	private BigDecimal price;
	private String isbn;
	private String availableForIssue;
	
}
