package com.dev.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.dev.cinema.dao",
        "com.dev.cinema.service"
})
public class AppConfig {
}
