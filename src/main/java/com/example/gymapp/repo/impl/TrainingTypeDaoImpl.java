package com.example.gymapp.repo.impl;

import com.example.gymapp.model.TrainingType;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainingTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class TrainingTypeDaoImpl implements TrainingTypeDao {
    private InMemoryStorage inMemoryStorage;
    private static final String NAMESPACE = "trainingTypes";
    private static final Logger LOGGER = Logger.getLogger(TrainingTypeDaoImpl.class.getName());

    @Autowired
    public void setInMemoryStorage(InMemoryStorage inMemoryStorage) {
        this.inMemoryStorage = inMemoryStorage;
    }

    @Override
    public void create(TrainingType trainingType) {
        trainingType.setId(inMemoryStorage.generateId(NAMESPACE));
        inMemoryStorage.getNamespace(NAMESPACE).put(trainingType.getId(), trainingType);
        LOGGER.log(Level.INFO, "Created training type: {0}", trainingType);
    }

    @Override
    public TrainingType select(Long id) {
        TrainingType trainingType = (TrainingType) inMemoryStorage.getNamespace(NAMESPACE).get(id);
        if (trainingType != null) {
            LOGGER.log(Level.INFO, "Selected training type: {0}", trainingType);
        } else {
            LOGGER.log(Level.WARNING, "Training type with ID {0} not found", id);
        }
        return trainingType;
    }

    @Override
    public List<TrainingType> selectAll() {
        List<TrainingType> trainingTypes = inMemoryStorage.getNamespace(NAMESPACE).values().stream()
                .map(obj -> (TrainingType) obj)
                .collect(java.util.stream.Collectors.toList());
        LOGGER.log(Level.INFO, "Selected all training types: {0}", trainingTypes.size());
        return trainingTypes;
    }

    @Override
    public void update(TrainingType trainingType) {
        inMemoryStorage.getNamespace(NAMESPACE).put(trainingType.getId(), trainingType);
        LOGGER.log(Level.INFO, "Updated training type: {0}", trainingType);
    }
}
