package com.dev.cinema.security.validation;

import com.dev.cinema.annotations.PasswordValidate;
import com.dev.cinema.dto.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordValidate, UserRequestDto> {
    @Override
    public void initialize(PasswordValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
