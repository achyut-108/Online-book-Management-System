package com.obms.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_master", schema = "gsg")
public class UserMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private BigInteger id;
	@Column(name = "name")
	private String name;
	@Column(name = "org_user_id")
	private String orgUserId;
	@Column(name = "active")
	private Integer active;
	@Column(name = "role_id")
	private Integer roleId;

}