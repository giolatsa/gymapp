package com.example.gymapp.dao;

import com.example.gymapp.TestConfig;
import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.Training;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainingDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TrainingDaoImplTest {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private InMemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void testCreateAndSelect() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setDateOfBirth(new Date());
        trainee.setAddress("123 Main St");

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setSpecialization("Yoga");

        Training training = new Training();
        training.setTrainingName("Morning Yoga");
        training.setTrainingType("Yoga");
        training.setTrainingDate(new Date());
        training.setTrainingDuration(60);
        training.setTrainee(trainee);
        training.setTrainer(trainer);

        trainingDao.create(training);
        Training fetchedTraining = trainingDao.select(training.getId());

        assertNotNull(fetchedTraining);
        assertEquals("Morning Yoga", fetchedTraining.getTrainingName());
        assertEquals("Yoga", fetchedTraining.getTrainingType());
        assertNotNull(fetchedTraining.getTrainee());
        assertNotNull(fetchedTraining.getTrainer());
    }


    @Test
    void testSelectAll() {
        Trainee trainee1 = new Trainee();
        trainee1.setId(1L);
        trainee1.setDateOfBirth(new Date());
        trainee1.setAddress("123 Main St");

        Trainer trainer1 = new Trainer();
        trainer1.setId(1L);
        trainer1.setSpecialization("Yoga");

        Training training1 = new Training();
        training1.setTrainingName("Morning Yoga");
        training1.setTrainingType("Yoga");
        training1.setTrainingDate(new Date());
        training1.setTrainingDuration(60);
        training1.setTrainee(trainee1);
        training1.setTrainer(trainer1);

        Trainee trainee2 = new Trainee();
        trainee2.setId(2L);
        trainee2.setDateOfBirth(new Date());
        trainee2.setAddress("456 Elm St");

        Trainer trainer2 = new Trainer();
        trainer2.setId(2L);
        trainer2.setSpecialization("Pilates");

        Training training2 = new Training();
        training2.setTrainingName("Evening Pilates");
        training2.setTrainingType("Pilates");
        training2.setTrainingDate(new Date());
        training2.setTrainingDuration(60);
        training2.setTrainee(trainee2);
        training2.setTrainer(trainer2);

        trainingDao.create(training1);
        trainingDao.create(training2);

        List<Training> trainings = trainingDao.selectAll();
        assertEquals(2, trainings.size());
    }
}
