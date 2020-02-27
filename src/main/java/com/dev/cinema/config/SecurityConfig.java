package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/register").permitAll()
                .and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .authorizeRequests().antMatchers("/cinema-halls/add").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/movies/add").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/movies").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/movie-sessions/add").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/orders").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/shoppingCarts").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
