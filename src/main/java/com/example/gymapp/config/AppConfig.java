package com.example.gymapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.example.gymapp")
@PropertySource("classpath:application.properties")
public class AppConfig {
}