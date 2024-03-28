package com.obms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.obms.domain.envlinks.EnironmentSetupLinks;

/**
 * @author saurabh.shyam
 * @since Feb 24, 2021 5:05 PM
 */
@Service
public interface EnvDetailService {

	public List<EnironmentSetupLinks> getEnvLinkDetails();

}
