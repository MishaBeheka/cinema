package com.dev.cinema.annotations;

import com.dev.cinema.security.validation.PasswordValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidate {
    String message() default "Password is wrong";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
