package com.example.gymapp.service.impl;

import com.example.gymapp.model.TrainingType;
import com.example.gymapp.repo.TrainingTypeDao;
import com.example.gymapp.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private final TrainingTypeDao trainingTypeDao;


    @Autowired
    public TrainingTypeServiceImpl(TrainingTypeDao trainingTypeDao) {
        this.trainingTypeDao = trainingTypeDao;
    }

    @Override
    public void createTrainingType(TrainingType trainingType) {
        trainingTypeDao.create(trainingType);
    }


    @Override
    public TrainingType getTrainingType(Long id) {
        return trainingTypeDao.select(id);
    }

    @Override
    public List<TrainingType> getAllTrainingTypes() {
        return trainingTypeDao.selectAll();
    }
}
