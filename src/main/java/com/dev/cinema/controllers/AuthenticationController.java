package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserRequestDto;

import com.dev.cinema.exceptoin.AuthenticationException;
import com.dev.cinema.service.impl.AuthenticationServiceImpl;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public String registration(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
        return "Registration successful";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody @Valid UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
        } catch (AuthenticationException e) {
            LOGGER.error("Login or password is incorrect " + e);
            return "Login or password is incorrect";
        }
        return "Authorization successful";
    }
}
