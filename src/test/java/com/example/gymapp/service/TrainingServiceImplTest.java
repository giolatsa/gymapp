package com.example.gymapp.service;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.Training;
import com.example.gymapp.repo.TrainingDao;
import com.example.gymapp.service.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {
    @Mock
    private TrainingDao trainingDao;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);

        Trainer trainer = new Trainer();
        trainer.setId(1L);

        Training training = new Training();
        training.setTrainingName("Morning Yoga");
        training.setTrainingType("Yoga");
        training.setTrainingDate(new Date());
        training.setTrainingDuration(60);
        training.setTrainee(trainee);
        training.setTrainer(trainer);

        trainingService.createTraining(training);

        verify(trainingDao, times(1)).create(training);
    }

    @Test
    void testGetTraining() {
        Training training = new Training();
        when(trainingDao.select(1L)).thenReturn(training);

        Training fetchedTraining = trainingService.getTraining(1L);
        assertNotNull(fetchedTraining);
        verify(trainingDao, times(1)).select(1L);
    }

    @Test
    void testGetAllTrainings() {
        Training training1 = new Training();
        Training training2 = new Training();
        when(trainingDao.selectAll()).thenReturn(Arrays.asList(training1, training2));

        List<Training> trainings = trainingService.getAllTrainings();
        assertEquals(2, trainings.size());
        verify(trainingDao, times(1)).selectAll();
    }
}
