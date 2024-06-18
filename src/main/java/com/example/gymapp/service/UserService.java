package com.example.gymapp.service;

import com.example.gymapp.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
}