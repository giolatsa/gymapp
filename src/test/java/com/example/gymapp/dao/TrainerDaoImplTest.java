package com.example.gymapp.dao;

import com.example.gymapp.TestConfig;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.TrainerDao;
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
class TrainerDaoImplTest {

    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private InMemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void testCreateAndSelect() {
        Trainer trainer = new Trainer();
        trainer.setSpecialization("Yoga");

        trainerDao.create(trainer);
        Trainer fetchedTrainer = trainerDao.select(trainer.getId());

        assertNotNull(fetchedTrainer);
        assertEquals("Yoga", fetchedTrainer.getSpecialization());
    }

    @Test
    void testUpdate() {
        Trainer trainer = new Trainer();
        trainer.setSpecialization("Yoga");

        trainerDao.create(trainer);
        trainer.setSpecialization("Pilates");
        trainerDao.update(trainer);
        Trainer fetchedTrainer = trainerDao.select(trainer.getId());

        assertNotNull(fetchedTrainer);
        assertEquals("Pilates", fetchedTrainer.getSpecialization());
    }

    @Test
    void testSelectAll() {
        Trainer trainer1 = new Trainer();
        trainer1.setSpecialization("Yoga");

        Trainer trainer2 = new Trainer();
        trainer2.setSpecialization("Pilates");

        trainerDao.create(trainer1);
        trainerDao.create(trainer2);

        List<Trainer> trainers = trainerDao.selectAll();
        assertEquals(2, trainers.size());
    }
}
