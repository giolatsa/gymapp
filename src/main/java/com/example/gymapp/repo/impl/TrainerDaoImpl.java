package com.example.gymapp.repo.impl;

import com.example.gymapp.model.Trainer;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class TrainerDaoImpl implements TrainerDao {
    private InMemoryStorage inMemoryStorage;
    private static final String NAMESPACE = "trainers";

    private static final Logger LOGGER = Logger.getLogger(TrainerDaoImpl.class.getName());

    @Autowired
    public void setInMemoryStorage(InMemoryStorage inMemoryStorage) {
        this.inMemoryStorage = inMemoryStorage;
    }

    @Override
    public void create(Trainer trainer) {
        trainer.setId(inMemoryStorage.generateId(NAMESPACE));
        inMemoryStorage.getNamespace(NAMESPACE).put(trainer.getId(), trainer);
        LOGGER.log(Level.INFO, "Created trainer: {0}", trainer);
    }

    @Override
    public void update(Trainer trainer) {
        if (inMemoryStorage.getNamespace(NAMESPACE).containsKey(trainer.getId())) {
            inMemoryStorage.getNamespace(NAMESPACE).put(trainer.getId(), trainer);
            LOGGER.log(Level.INFO, "Updated trainer: {0}", trainer);
        } else {
            LOGGER.log(Level.WARNING, "Trainer with ID {0} not found for update", trainer.getId());
        }
    }

    @Override
    public Trainer select(Long id) {
        Trainer trainer = (Trainer) inMemoryStorage.getNamespace(NAMESPACE).get(id);
        if (trainer != null) {
            LOGGER.log(Level.INFO, "Selected trainer: {0}", trainer);
        } else {
            LOGGER.log(Level.WARNING, "Trainer with ID {0} not found", id);
        }
        return trainer;
    }


    @Override
    public List<Trainer> selectAll() {
        List<Trainer> trainers = inMemoryStorage.getNamespace(NAMESPACE).values().stream()
                .map(obj -> (Trainer) obj)
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "Selected all trainers: {0}", trainers.size());
        return trainers;

    }





}
