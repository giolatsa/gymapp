package com.example.gymapp.service.impl;

import com.example.gymapp.model.User;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User get(Long id) {
        return userDao.select(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }
}