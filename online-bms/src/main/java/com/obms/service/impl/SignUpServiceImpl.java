package com.obms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obms.domain.inventory.NewJoineeSignUpRequest;
import com.obms.domain.inventory.NewJoineeSignUpResponse;
import com.obms.entity.UserMasterEntity;
import com.obms.repo.UserMasterRepository;
import com.obms.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService{

	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@Override
	public NewJoineeSignUpResponse signUp(NewJoineeSignUpRequest newJoineeSignUpRequest) {
		
		NewJoineeSignUpResponse newJoineeSignUpResponse = new NewJoineeSignUpResponse();
		
		UserMasterEntity userMasterEntity = new UserMasterEntity();
		userMasterEntity.setActive(1);
		userMasterEntity.setId(newJoineeSignUpRequest.getJoineeId());
		
		
		//newJoineeSignUpResponse.set
		
		return newJoineeSignUpResponse;
		
	}
}
