package com.example.gymapp.service.impl;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.User;
import com.example.gymapp.repo.TraineeDao;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.TraineeService;
import com.example.gymapp.utils.ProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final TraineeDao traineeDao;

    private final UserDao userDao;

    @Autowired
    public TraineeServiceImpl(TraineeDao traineeDao, UserDao userDao) {
        this.traineeDao = traineeDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Trainee trainee) {
        List<User> users = userDao.selectAll();
        List<String> existingUsernames = users.stream().map(User::getUsername).toList();

        User user = trainee.getUser();
        user.setUsername(ProfileUtils.generateUsername(user.getFirstName(), user.getLastName(), existingUsernames));
        user.setPassword(ProfileUtils.generatePassword());

        userDao.create(user);
        traineeDao.create(trainee);
    }

    @Override
    public void update(Trainee trainee) {
        traineeDao.update(trainee);
    }

    @Override
    public Trainee get(Long id) {
        return traineeDao.select(id);
    }

    @Override
    public List<Trainee> getAll() {
        return traineeDao.selectAll();
    }
}
