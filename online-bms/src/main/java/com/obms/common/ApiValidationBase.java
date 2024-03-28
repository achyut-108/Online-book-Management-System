package com.obms.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class ApiValidationBase {

    private List<ValidationError> validationErrors;

    public void addValidationError(ValidationError validationError){
        if(Objects.isNull(this.validationErrors)) this.validationErrors = new ArrayList<ValidationError>();
        this.validationErrors.add(validationError);
    }

    public void addValidationError(String errorCode,String errorDescription,String fieldName,Object fieldValue){
        this.addValidationError(new ValidationError(errorCode,errorDescription,fieldName,fieldValue));
    }


}
