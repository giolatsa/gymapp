package com.example.gymapp.repo;

import com.example.gymapp.model.TrainingType;

import java.util.List;

public interface TrainingTypeDao {
    void create(TrainingType trainingType);
    TrainingType select(Long id);
    List<TrainingType> selectAll();
    void update(TrainingType trainingType);
}