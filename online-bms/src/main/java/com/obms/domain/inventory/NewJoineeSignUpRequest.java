package com.obms.domain.inventory;

import java.io.Serializable;
import java.math.BigInteger;
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
public class NewJoineeSignUpRequest extends CommonServiceRequest implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private BigInteger joineeId;
	private String name;
	private String role;
	private Long departmentId;//--dpartmentMAster
	private String teamName;
	private String projectId;//---ProjectMaster
	private Date joinningDate;
	private String profileId;//--profileMaster
}
