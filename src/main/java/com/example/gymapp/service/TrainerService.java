package com.example.gymapp.service;

import com.example.gymapp.model.Trainer;

import java.util.List;

public interface TrainerService {
    void createTrainer(Trainer trainer);
    void updateTrainer(Trainer trainer);
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
}