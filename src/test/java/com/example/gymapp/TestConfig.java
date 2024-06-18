package com.example.gymapp;

import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.repo.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.gymapp")
public class TestConfig {

    @Bean
    public InMemoryStorage inMemoryStorage() {
        return new InMemoryStorage();
    }

    @Bean
    public UserDao userDao(InMemoryStorage storage) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setStorage(storage);
        return userDao;
    }

}