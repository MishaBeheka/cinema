package com.dev.cinema.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserResponseDto {
    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
