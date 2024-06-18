package com.example.gymapp.repo.impl;

import com.example.gymapp.model.User;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {
    private InMemoryStorage storage;
    private static final String NAMESPACE = "users";
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());


    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(User user) {
        user.setId(storage.generateId(NAMESPACE));
        storage.getNamespace(NAMESPACE).put(user.getId(), user);
        LOGGER.log(Level.INFO, "Created user: {0}", user);

    }

    @Override
    public void update(User user) {
        if (storage.getNamespace(NAMESPACE).containsKey(user.getId())) {
            storage.getNamespace(NAMESPACE).put(user.getId(), user);
            LOGGER.log(Level.INFO, "Updated user: {0}", user);
        } else {
            LOGGER.log(Level.WARNING, "User with ID {0} not found for update", user.getId());
        }

    }

    @Override
    public User select(Long id) {
        User user = (User) storage.getNamespace(NAMESPACE).get(id);
        if (user != null) {
            LOGGER.log(Level.INFO, "Selected user: {0}", user);
        } else {
            LOGGER.log(Level.WARNING, "User with ID {0} not found", id);
        }
        return user;

    }

    @Override
    public List<User> selectAll() {
        List<User> users = storage.getNamespace(NAMESPACE).values().stream()
                .map(obj -> (User) obj)
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "Selected all users");
        return users;
    }
}
