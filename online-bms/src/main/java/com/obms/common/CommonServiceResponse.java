package com.obms.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommonServiceResponse extends ServiceStatus implements Serializable{
	public static final long serialVersionUID = 1L;
	
	
	
}
