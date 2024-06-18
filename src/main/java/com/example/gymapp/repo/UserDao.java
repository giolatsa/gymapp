package com.example.gymapp.repo;

import com.example.gymapp.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void update(User user);
    User select(Long id);
    List<User> selectAll();
}