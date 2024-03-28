package com.obms.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStatus extends ApiValidationBase implements Serializable {

	public static final long serialVersionUID = 1L;
	private Boolean success;
	private String message;
}
