package com.dev.cinema.dto;

import com.dev.cinema.annotations.EmailValidate;
import javax.validation.constraints.NotEmpty;

public class UserRegisterRequestDto {
    @NotEmpty
    @EmailValidate
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
