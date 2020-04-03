package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InitializeDataController {

    private final RoleService roleService;
    private final UserService userService;

    public InitializeDataController(RoleService roleService,
                                    UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
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
        user.setPassword("user1");
        user.getRoles().add(roleService.getRole("USER"));
        userService.add(user);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin1");
        admin.getRoles().add(roleService.getRole("ADMIN"));
        userService.add(admin);
    }
}
