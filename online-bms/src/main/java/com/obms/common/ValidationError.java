package com.obms.common;

import lombok.Data;

@Data
public class ValidationError {

	private String errorCode;
	private String errorDescription;
	private String fieldName;
	private Object fieldValue;

	public ValidationError() {
	}

	public ValidationError(String errorCode, String errorDescription, String fieldName, Object fieldValue) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
