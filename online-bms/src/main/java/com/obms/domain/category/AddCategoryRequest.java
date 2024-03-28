package com.obms.domain.category;

import java.io.Serializable;

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
public class AddCategoryRequest extends CommonServiceRequest implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private String categoryName;
	private String categoryDescription;
	
}
