package com.obms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.obms.domain.envlinks.EnironmentSetupLinks;
import com.obms.service.EnvDetailService;

/**
 * @author saurabh.shyam
 * @since Feb 24, 2021 5:05 PM
 */
@Service
public class EnvDetailServiceImpl implements EnvDetailService {

	@Override
	public List<EnironmentSetupLinks> getEnvLinkDetails() {
		List<EnironmentSetupLinks> result = new ArrayList<>();
		result.add(new EnironmentSetupLinks(1l, "New Hire Page",
				"https://confluence.ihsmarkit.com/pages/viewpage.action?spaceKey=IMDev&title=New+Hire+Onboarding+Guide"));
		result.add(new EnironmentSetupLinks(2l, "Oracle Setup",
				"https://confluence.ihsmarkit.com/display/IMDev/Oracle+Personal+Edition+Install+for+Unittesting"));
		result.add(new EnironmentSetupLinks(3l, "Unit Test Setup",
				"https://confluence.ihsmarkit.com/display/IMDev/Team+Panthers#How%20to%20setup%20UNITTEST%20DB?"));
		result.add(new EnironmentSetupLinks(4l, "Intellij Setup",
				"https://confluence.ihsmarkit.com/pages/viewpage.action?spaceKey=IMDev&title=Intellij"));
		result.add(new EnironmentSetupLinks(5l, "Common Troubleshooting",
				"https://confluence.ihsmarkit.com/display/IMDev/Troubleshooting+and+Debugging+Tips"));
		return result;

	}
}
