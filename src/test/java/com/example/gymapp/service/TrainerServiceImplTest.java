package com.example.gymapp.service;

import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.User;
import com.example.gymapp.repo.TrainerDao;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.impl.TrainerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {
    @Mock
    private TrainerDao trainerDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainer() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        Trainer trainer = new Trainer();
        trainer.setUser(user);

        when(userDao.selectAll()).thenReturn(Arrays.asList());

        trainerService.createTrainer(trainer);

        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        verify(userDao, times(1)).create(user);
        verify(trainerDao, times(1)).create(trainer);
    }

    @Test
    void testGetTrainer() {
        Trainer trainer = new Trainer();
        when(trainerDao.select(1L)).thenReturn(trainer);

        Trainer fetchedTrainer = trainerService.getTrainer(1L);
        assertNotNull(fetchedTrainer);
        verify(trainerDao, times(1)).select(1L);
    }

    @Test
    void testGetAllTrainers() {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        when(trainerDao.selectAll()).thenReturn(Arrays.asList(trainer1, trainer2));

        List<Trainer> trainers = trainerService.getAllTrainers();
        assertEquals(2, trainers.size());
        verify(trainerDao, times(1)).selectAll();
    }
}
