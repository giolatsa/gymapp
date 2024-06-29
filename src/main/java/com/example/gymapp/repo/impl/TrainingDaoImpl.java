package com.example.gymapp.repo.impl;

import com.example.gymapp.model.Training;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class TrainingDaoImpl implements TrainingDao {
    private InMemoryStorage inMemoryStorage;
    private static final String NAMESPACE = "trainings";
    private static final Logger LOGGER = Logger.getLogger(TrainingDaoImpl.class.getName());


    @Autowired
    public void setInMemoryStorage(InMemoryStorage inMemoryStorage) {
        this.inMemoryStorage = inMemoryStorage;
    }

    @Override
    public void create(Training training) {
        training.setId(inMemoryStorage.generateId(NAMESPACE));
        inMemoryStorage.getNamespace(NAMESPACE).put(training.getId(), training);
        LOGGER.log(Level.INFO, "Created training: {0}", training);
    }


    @Override
    public Training select(Long id) {
        Training training = (Training) inMemoryStorage.getNamespace(NAMESPACE).get(id);
        if (training != null) {
            LOGGER.log(Level.INFO, "Selected training: {0}", training);
        } else {
            LOGGER.log(Level.WARNING, "Training with ID {0} not found", id);
        }
        return training;
    }


    @Override
    public List<Training> selectAll() {
        List<Training> trainings = inMemoryStorage.getNamespace(NAMESPACE).values().stream()
                .map(obj -> (Training) obj)
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "Selected all trainings: {0}", trainings.size());
        return trainings;
    }

    @Override
    public void update(Training training) {
        inMemoryStorage.getNamespace(NAMESPACE).put(training.getId(), training);
        LOGGER.log(Level.INFO, "Updated training: {0}", training);

    }
}
