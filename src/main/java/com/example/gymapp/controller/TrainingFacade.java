package com.example.gymapp.controller;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.Training;
import com.example.gymapp.model.User;
import com.example.gymapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingFacade {
    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
    private final TrainingTypeService trainingTypeService;

    @Autowired
    public TrainingFacade(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService, TrainingTypeService trainingTypeService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
        this.trainingTypeService = trainingTypeService;
    }

    public User getUserById(Long id) {
        return userService.getUser(id);
    }

    public void createUser(User user) {
        userService.createUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public Trainee createTrainee(Trainee trainee) {
        traineeService.createTrainee(trainee);
        return trainee;
    }

    public Trainee getTraineeById(Long id) {
        return traineeService.getTrainee(id);
    }

    public List<Trainee> getAllTrainees() {
        return traineeService.getAllTrainees();
    }

    public void updateTrainee(Trainee trainee) {
        traineeService.updateTrainee(trainee);
    }


    public void createTraining(Training training) {
        trainingService.createTraining(training);
    }

    public Training getTrainingById(Long id) {
        return trainingService.getTraining(id);
    }

    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    public void createTrainer(Trainer trainer) {
        trainerService.createTrainer(trainer);
    }

    public Trainer getTrainerById(Long id) {
        return trainerService.getTrainer(id);
    }

    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    public void updateTrainer(Trainer trainer) {
        trainerService.updateTrainer(trainer);
    }

}