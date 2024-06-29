package com.example.gymapp.service;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.User;
import com.example.gymapp.repo.TraineeDao;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.impl.TraineeServiceImpl;
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

class TraineeServiceImplTest {
    @Mock
    private TraineeDao traineeDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        Trainee trainee = new Trainee();
        trainee.setUser(user);

        when(userDao.selectAll()).thenReturn(Arrays.asList());

        traineeService.create(trainee);

        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        verify(userDao, times(1)).create(user);
        verify(traineeDao, times(1)).create(trainee);
    }

    @Test
    void testGetTrainee() {
        Trainee trainee = new Trainee();
        when(traineeDao.select(1L)).thenReturn(trainee);

        Trainee fetchedTrainee = traineeService.get(1L);
        assertNotNull(fetchedTrainee);
        verify(traineeDao, times(1)).select(1L);
    }

    @Test
    void testGetAllTrainees() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        when(traineeDao.selectAll()).thenReturn(Arrays.asList(trainee1, trainee2));

        List<Trainee> trainees = traineeService.getAll();
        assertEquals(2, trainees.size());
        verify(traineeDao, times(1)).selectAll();
    }
}
