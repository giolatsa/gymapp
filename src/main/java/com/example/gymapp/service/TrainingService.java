package com.example.gymapp.service;

import com.example.gymapp.model.Training;

import java.util.List;

public interface TrainingService {
    void createTraining(Training training);
    Training getTraining(Long id);
    List<Training> getAllTrainings();
}