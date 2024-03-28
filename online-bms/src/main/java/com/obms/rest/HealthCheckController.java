package com.obms.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HealthCheckController {

	@GetMapping("health-check/get")
	public boolean healthCheck() {
		return true;
	}
	
	
	@GetMapping("health-check/brum-config/get")
	public boolean brumConfig() {
		return true;
	}
}
