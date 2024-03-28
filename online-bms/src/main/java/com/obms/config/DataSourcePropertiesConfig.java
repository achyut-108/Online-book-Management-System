package com.obms.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DataSourcePropertiesConfig {
	
	private String url;
	private String userId;
	private String password;
	private String driverName;
	
}
