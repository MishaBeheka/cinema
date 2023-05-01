package com.dev.cinema.annotations;

import com.dev.cinema.security.validation.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidate {
    String message() default "Password is wrong";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
