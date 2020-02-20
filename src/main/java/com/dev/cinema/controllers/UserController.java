package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserRequestDto;
import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public User create(@RequestBody UserRequestDto userRequestDto) {
        return userService.add(buildUser(userRequestDto));
    }

    @GetMapping(value = "/byEmail")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return buildUserResponseDto(userService.findByEmail(email));
    }

    private User buildUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    private UserResponseDto buildUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
