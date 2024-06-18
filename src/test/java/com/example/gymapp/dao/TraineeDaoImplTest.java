package com.example.gymapp.dao;

import com.example.gymapp.TestConfig;
import com.example.gymapp.model.Trainee;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TraineeDao;
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
class TraineeDaoImplTest {

    @Autowired
    private TraineeDao traineeDao;

    @Autowired
    private InMemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void testCreateAndSelect() {
        Trainee trainee = new Trainee();
        trainee.setDateOfBirth(new Date());
        trainee.setAddress("123 Main St");

        traineeDao.create(trainee);
        Trainee fetchedTrainee = traineeDao.select(trainee.getId());

        assertNotNull(fetchedTrainee);
        assertEquals("123 Main St", fetchedTrainee.getAddress());
    }

    @Test
    void testUpdate() {
        Trainee trainee = new Trainee();
        trainee.setDateOfBirth(new Date());
        trainee.setAddress("123 Main St");

        traineeDao.create(trainee);
        trainee.setAddress("456 Elm St");
        traineeDao.update(trainee);
        Trainee fetchedTrainee = traineeDao.select(trainee.getId());

        assertNotNull(fetchedTrainee);
        assertEquals("456 Elm St", fetchedTrainee.getAddress());
    }

    @Test
    void testSelectAll() {
        Trainee trainee1 = new Trainee();
        trainee1.setDateOfBirth(new Date());
        trainee1.setAddress("123 Main St");

        Trainee trainee2 = new Trainee();
        trainee2.setDateOfBirth(new Date());
        trainee2.setAddress("456 Elm St");

        traineeDao.create(trainee1);
        traineeDao.create(trainee2);

        List<Trainee> trainees = traineeDao.selectAll();
        assertEquals(2, trainees.size());
    }
}
