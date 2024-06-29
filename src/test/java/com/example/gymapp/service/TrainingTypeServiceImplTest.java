package com.example.gymapp.service;

import com.example.gymapp.model.TrainingType;
import com.example.gymapp.repo.TrainingTypeDao;
import com.example.gymapp.service.impl.TrainingTypeServiceImpl;
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

class TrainingTypeServiceImplTest {
    @Mock
    private TrainingTypeDao trainingTypeDao;

    @InjectMocks
    private TrainingTypeServiceImpl trainingTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainingType() {
        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName("Yoga");

        trainingTypeService.create(trainingType);

        verify(trainingTypeDao, times(1)).create(trainingType);
    }

    @Test
    void testGetTrainingType() {
        TrainingType trainingType = new TrainingType();
        when(trainingTypeDao.select(1L)).thenReturn(trainingType);

        TrainingType fetchedTrainingType = trainingTypeService.get(1L);
        assertNotNull(fetchedTrainingType);
        verify(trainingTypeDao, times(1)).select(1L);
    }

    @Test
    void testGetAllTrainingTypes() {
        TrainingType trainingType1 = new TrainingType();
        TrainingType trainingType2 = new TrainingType();
        when(trainingTypeDao.selectAll()).thenReturn(Arrays.asList(trainingType1, trainingType2));

        List<TrainingType> trainingTypes = trainingTypeService.getAll();
        assertEquals(2, trainingTypes.size());
        verify(trainingTypeDao, times(1)).selectAll();
    }
}
