package com.dev.cinema.security.validation;

import com.dev.cinema.annotations.PasswordValidate;
import com.dev.cinema.dto.UserRegisterRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordValidate, UserRegisterRequestDto> {
    @Override
    public void initialize(PasswordValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserRegisterRequestDto userRegisterRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRegisterRequestDto.getPassword()
                .equals(userRegisterRequestDto.getRepeatPassword());
    }
}
