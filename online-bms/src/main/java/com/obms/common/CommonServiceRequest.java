package com.obms.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommonServiceRequest implements Serializable{

	public static final long serialVersionUID = 1L;
	private String userId;
}
