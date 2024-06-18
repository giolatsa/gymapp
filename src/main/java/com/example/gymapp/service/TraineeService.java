package com.example.gymapp.service;

import com.example.gymapp.model.Trainee;

import java.util.List;

public interface TraineeService {
    void createTrainee(Trainee trainee);
    void updateTrainee(Trainee trainee);
    Trainee getTrainee(Long id);
    List<Trainee> getAllTrainees();
}