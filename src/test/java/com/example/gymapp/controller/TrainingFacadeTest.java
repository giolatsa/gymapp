package com.example.gymapp.controller;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.Training;
import com.example.gymapp.model.User;
import com.example.gymapp.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingFacadeTest {

    @Mock
    private UserService userService;

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @Mock
    private TrainingTypeService trainingTypeService;

    @InjectMocks
    private TrainingFacade trainingFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        when(userService.get(1L)).thenReturn(user);

        User fetchedUser = trainingFacade.getUserById(1L);
        assertNotNull(fetchedUser);
        verify(userService, times(1)).get(1L);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        trainingFacade.createUser(user);
        verify(userService, times(1)).create(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        trainingFacade.updateUser(user);
        verify(userService, times(1)).update(user);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = trainingFacade.getAllUsers();
        assertEquals(2, users.size());
        verify(userService, times(1)).getAll();
    }

    @Test
    void testCreateTrainee() {
        Trainee trainee = new Trainee();
        trainingFacade.createTrainee(trainee);
        verify(traineeService, times(1)).create(trainee);
    }

    @Test
    void testGetTraineeById() {
        Trainee trainee = new Trainee();
        when(traineeService.get(1L)).thenReturn(trainee);

        Trainee fetchedTrainee = trainingFacade.getTraineeById(1L);
        assertNotNull(fetchedTrainee);
        verify(traineeService, times(1)).get(1L);
    }

    @Test
    void testGetAllTrainees() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        when(traineeService.getAll()).thenReturn(Arrays.asList(trainee1, trainee2));

        List<Trainee> trainees = trainingFacade.getAllTrainees();
        assertEquals(2, trainees.size());
        verify(traineeService, times(1)).getAll();
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        trainingFacade.updateTrainee(trainee);
        verify(traineeService, times(1)).update(trainee);
    }

    @Test
    void testCreateTraining() {
        Training training = new Training();
        trainingFacade.createTraining(training);
        verify(trainingService, times(1)).create(training);
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training();
        when(trainingService.get(1L)).thenReturn(training);

        Training fetchedTraining = trainingFacade.getTrainingById(1L);
        assertNotNull(fetchedTraining);
        verify(trainingService, times(1)).get(1L);
    }

    @Test
    void testGetAllTrainings() {
        Training training1 = new Training();
        Training training2 = new Training();
        when(trainingService.getAll()).thenReturn(Arrays.asList(training1, training2));

        List<Training> trainings = trainingFacade.getAllTrainings();
        assertEquals(2, trainings.size());
        verify(trainingService, times(1)).getAll();
    }

    @Test
    void testCreateTrainer() {
        Trainer trainer = new Trainer();
        trainingFacade.createTrainer(trainer);
        verify(trainerService, times(1)).create(trainer);
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer = new Trainer();
        when(trainerService.get(1L)).thenReturn(trainer);

        Trainer fetchedTrainer = trainingFacade.getTrainerById(1L);
        assertNotNull(fetchedTrainer);
        verify(trainerService, times(1)).get(1L);
    }

    @Test
    void testGetAllTrainers() {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        when(trainerService.getAll()).thenReturn(Arrays.asList(trainer1, trainer2));

        List<Trainer> trainers = trainingFacade.getAllTrainers();
        assertEquals(2, trainers.size());
        verify(trainerService, times(1)).getAll();
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer();
        trainingFacade.updateTrainer(trainer);
        verify(trainerService, times(1)).update(trainer);
    }
}
