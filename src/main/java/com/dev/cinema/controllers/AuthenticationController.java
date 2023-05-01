package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserLoginRequestDto;
import com.dev.cinema.dto.UserRegisterRequestDto;
import com.dev.cinema.exceptoin.AuthenticationException;
import com.dev.cinema.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthenticationController {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/register")
    public String showRegisterForm() {
        return "registration";
    }

    @PostMapping(value = "/register")
    public RedirectView registration(@Valid UserRegisterRequestDto userRegisterRequestDto) {
        authenticationService.register(userRegisterRequestDto.getEmail(),
                userRegisterRequestDto.getPassword());
        return new RedirectView("/");
    }

    @GetMapping(value = "/login")
    public String showLoginForm() {
        return "loginForm";
    }

    @PostMapping(value = "/login")
    public RedirectView login(UserLoginRequestDto userLoginRequestDto) {
        try {
            authenticationService.login(userLoginRequestDto.getEmail(),
                    userLoginRequestDto.getPassword());
        } catch (AuthenticationException e) {
            LOGGER.error("Login or password is incorrect " + e);
            return new RedirectView("/login");
        }
        LOGGER.info("Authorization successful " + userLoginRequestDto.getEmail());
        return new RedirectView("/");
    }
}
