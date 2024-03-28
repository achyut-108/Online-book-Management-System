package com.obms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obms.domain.envlinks.EnironmentSetupLinks;
import com.obms.service.impl.EnvDetailServiceImpl;

/**
 * @author saurabh.shyam
 * @since Feb 24, 2021 4:58 PM
 */
@RestController
@RequestMapping("/gsg/env")
public class EnvDetailController {

	@Autowired
	EnvDetailServiceImpl envDetailService;

	@GetMapping("/getSetupDetail/get")
	public List<EnironmentSetupLinks> getEnvLinkLinks() {
		return envDetailService.getEnvLinkDetails();
	}

}
