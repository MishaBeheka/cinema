package com.dev.cinema.controllers;

import com.dev.cinema.exceptoin.AuthenticationException;
import com.dev.cinema.service.impl.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public String registration(@RequestParam String email,
                               @RequestParam String password) {
        authenticationService.register(email, password);
        return "Registration successful";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        try {
            authenticationService.login(email, password);
        } catch (AuthenticationException e) {
            return "Login or password is incorrect";
        }
        return "Authorization successful";
    }
}
