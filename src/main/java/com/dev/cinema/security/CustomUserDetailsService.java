package com.dev.cinema.security;

import static org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        } else {
            UserBuilder builder = withUsername(email);
            builder.password(user.getPassword());
            builder.roles(getRoles(user));
            return builder.build();
        }
    }

    private String[] getRoles(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getRole)
                .toArray(String[]::new);
    }
}
