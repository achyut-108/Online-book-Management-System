package com.obms.common.util;

import java.util.Collections;
import java.util.Objects;

import com.obms.common.CommonServiceResponse;

public class ServiceUtil {

	public static void setSuccess(CommonServiceResponse serviceResponse) {
		if(Objects.nonNull(serviceResponse)) {
			//serviceResponse.setMessage("SUCCESS");
			serviceResponse.setSuccess(true);
			serviceResponse.setValidationErrors(Collections.EMPTY_LIST);
		}
	}
	
	public static void setFailure(CommonServiceResponse serviceResponse) {
		if(Objects.nonNull(serviceResponse)) {
			//serviceResponse.setMessage("FAILURE");
			serviceResponse.setSuccess(false);
			serviceResponse.setValidationErrors(Collections.EMPTY_LIST);
		}
	}
}
