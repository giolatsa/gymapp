package com.example.gymapp.repo.impl;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TraineeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@Repository
public class TraineeDaoIml implements TraineeDao {
    private InMemoryStorage inMemoryStorage;
    private static final String NAMESPACE = "trainees";

    private static final Logger LOGGER = Logger.getLogger(TrainerDaoImpl.class.getName());
    @Autowired
    public void setInMemoryStorage(InMemoryStorage inMemoryStorage) {
        this.inMemoryStorage = inMemoryStorage;
    }

    @Override
    public void create(Trainee trainee) {

        trainee.setId(inMemoryStorage.generateId(NAMESPACE));

        inMemoryStorage.getNamespace(NAMESPACE).put(trainee.getId(), trainee);
        LOGGER.log(Level.INFO, "Created trainee: {0}", trainee);

    }

    @Override
    public void update(Trainee trainee) {
        if (inMemoryStorage.getNamespace(NAMESPACE).containsKey(trainee.getId())) {
            inMemoryStorage.getNamespace(NAMESPACE).put(trainee.getId(), trainee);
            LOGGER.log(Level.INFO, "Updated trainee: {0}", trainee);
        } else {
            LOGGER.log(Level.WARNING, "Trainee with ID {0} not found for update", trainee.getId());
        }
    }

    @Override
    public Trainee select(Long id) {
        Trainee trainee = (Trainee) inMemoryStorage.getNamespace(NAMESPACE).get(id);
        if (trainee != null) {
            LOGGER.log(Level.INFO, "Selected trainee: {0}", trainee);
        } else {
            LOGGER.log(Level.WARNING, "Trainee with ID {0} not found", id);
        }
        return trainee;

    }

    @Override
    public List<Trainee> selectAll() {
        List<Trainee> trainees = inMemoryStorage.getNamespace(NAMESPACE).values().stream()
                .map(obj -> (Trainee) obj)
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "Selected all trainees: {0}", trainees.size());
        return trainees;
    }



}
