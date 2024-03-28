package com.obms.domain.author;

import java.io.Serializable;

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
public class AuthorResponse extends CommonServiceResponse implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private Integer authorId;
	private String authorName;
	private String authorDescription;
	
}
