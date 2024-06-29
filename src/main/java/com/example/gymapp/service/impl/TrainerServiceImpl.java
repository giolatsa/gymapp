package com.example.gymapp.service.impl;

import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.User;
import com.example.gymapp.repo.TrainerDao;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.TrainerService;
import com.example.gymapp.utils.ProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerDao trainerDao;
    private final UserDao userDao;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao, UserDao userDao) {
        this.trainerDao = trainerDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Trainer trainer) {
        List<User> users = userDao.selectAll();
        List<String> existingUsernames = users.stream().map(User::getUsername).toList();

        User user = trainer.getUser();
        user.setUsername(ProfileUtils.generateUsername(user.getFirstName(), user.getLastName(), existingUsernames));
        user.setPassword(ProfileUtils.generatePassword());

        userDao.create(user);
        trainerDao.create(trainer);
    }

    @Override
    public void update(Trainer trainer) {
        trainerDao.update(trainer);
    }

    @Override
    public Trainer get(Long id) {
        return trainerDao.select(id);
    }


    @Override
    public List<Trainer> getAll() {
        return trainerDao.selectAll();
    }




}
