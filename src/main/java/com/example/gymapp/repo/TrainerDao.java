package com.example.gymapp.repo;

import com.example.gymapp.model.Trainer;

import java.util.List;

public interface TrainerDao {
    void create(Trainer trainer);
    void update(Trainer trainer);
    Trainer select(Long id);
    List<Trainer> selectAll();
}