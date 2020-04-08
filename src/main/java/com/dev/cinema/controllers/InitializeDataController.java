package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitializeDataController {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InitializeDataController(RoleService roleService,
                                    UserService userService,
                                    PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initialize() {
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        Role userRole = new Role();
        userRole.setRole("USER");
        roleService.add(adminRole);
        roleService.add(userRole);

        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword(passwordEncoder.encode("user1"));
        userService.add(user);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin1"));
        admin.getRoles().add(roleService.getRole("ADMIN"));
        userService.add(admin);
    }
}
