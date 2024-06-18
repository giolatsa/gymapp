package com.example.gymapp.repo;

import com.example.gymapp.model.Trainee;

import java.util.List;

public interface TraineeDao {
    void create(Trainee trainee);
    void update(Trainee trainee);
    Trainee select(Long id);
    List<Trainee> selectAll();
}