package com.example.gymapp.service.impl;

import com.example.gymapp.model.Training;
import com.example.gymapp.repo.TrainingDao;
import com.example.gymapp.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingDao trainingDao;


    @Autowired
    public TrainingServiceImpl(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public void create(Training training) {
        trainingDao.create(training);
    }
    @Override
    public void update(Training entity) {
        trainingDao.update(entity);
    }

    @Override
    public Training get(Long id) {
        return trainingDao.select(id);
    }

    @Override
    public List<Training> getAll() {
        return trainingDao.selectAll();
    }
}
