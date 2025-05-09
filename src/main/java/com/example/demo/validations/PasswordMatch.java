package com.example.demo.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PasswordMatch {

    String message() default "the password and confirm password should be the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
