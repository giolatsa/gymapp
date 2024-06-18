package com.example.gymapp.repo;

import com.example.gymapp.model.Training;

import java.util.List;

public interface TrainingDao {
    void create(Training training);
    Training select(Long id);
    List<Training> selectAll();
}