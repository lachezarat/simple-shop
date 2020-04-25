package com.myproject.eshop.validation;

import com.myproject.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !(userService.isUsernameAlreadyInUse(value));
    }
}
