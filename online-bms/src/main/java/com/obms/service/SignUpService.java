package com.obms.service;

import com.obms.domain.inventory.NewJoineeSignUpRequest;
import com.obms.domain.inventory.NewJoineeSignUpResponse;

public interface SignUpService {

	public NewJoineeSignUpResponse signUp(NewJoineeSignUpRequest newJoineeSignUpRequest);
}
