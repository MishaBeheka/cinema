package com.dev.cinema.service.impl;

import com.dev.cinema.exceptoin.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            String hashPassword = HashUtil.hashSalt(password, user.getSalt());
            if (hashPassword.equals(user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Incorrect login or password ");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User newUser = userService.add(user);
        shoppingCartService.registerNewShoppingCart(newUser);
        return newUser;
    }
}
