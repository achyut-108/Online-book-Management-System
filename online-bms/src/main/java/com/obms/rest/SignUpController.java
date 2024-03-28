package com.obms.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obms.domain.inventory.NewJoineeSignUpRequest;
import com.obms.domain.inventory.NewJoineeSignUpResponse;
import com.obms.repo.UserMasterRepository;

@RestController
@RequestMapping("gsg/new-joinee")
public class SignUpController {

	private static final Logger log = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@PostMapping(value="/sign-up/post",produces="application/json")
	public NewJoineeSignUpResponse signUp(@RequestBody NewJoineeSignUpRequest newJoineeSignUpRequest) {
		log.info("request : {}", newJoineeSignUpRequest.toString());
		
		log.info("userMasterRepo : {}",userMasterRepository.findAll());
		
		return new NewJoineeSignUpResponse();			
	}
	
	@PostMapping(value="/ab2/post",produces="application/json")
	public NewJoineeSignUpResponse signUp2() {
		//log.info("request : {}", newJoineeSignUpRequest.toString());
		return new NewJoineeSignUpResponse();			
	}
}
