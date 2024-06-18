package com.example.gymapp.service;

import com.example.gymapp.model.TrainingType;

import java.util.List;

public interface TrainingTypeService {
    void createTrainingType(TrainingType trainingType);
    TrainingType getTrainingType(Long id);
    List<TrainingType> getAllTrainingTypes();
}