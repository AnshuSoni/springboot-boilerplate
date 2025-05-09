package com.example.demo.validations;

import com.example.demo.dto.SignupRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o instanceof SignupRequest signupRequest) {
            return signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
        }
        return false;
    }
}
