package com.example.gymapp.dao;

import com.example.gymapp.TestConfig;
import com.example.gymapp.model.TrainingType;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainingTypeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class TrainingTypeDaoImplTest {

    @Autowired
    private TrainingTypeDao trainingTypeDao;

    @Autowired
    private InMemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void testCreateAndSelect() {
        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName("Yoga");

        trainingTypeDao.create(trainingType);
        TrainingType fetchedTrainingType = trainingTypeDao.select(trainingType.getId());

        assertNotNull(fetchedTrainingType);
        assertEquals("Yoga", fetchedTrainingType.getTrainingTypeName());
    }


    @Test
    void testSelectAll() {
        TrainingType trainingType1 = new TrainingType();
        trainingType1.setTrainingTypeName("Yoga");

        TrainingType trainingType2 = new TrainingType();
        trainingType2.setTrainingTypeName("Pilates");

        trainingTypeDao.create(trainingType1);
        trainingTypeDao.create(trainingType2);

        List<TrainingType> trainingTypes = trainingTypeDao.selectAll();
        assertEquals(2, trainingTypes.size());
    }
}
