package com.example.gymapp.repo;

import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.Trainer;
import com.example.gymapp.model.Training;
import com.example.gymapp.model.TrainingType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class InMemoryStorage {
    private Map<String, Map<Long, Object>> storage = new HashMap<>();
    private Map<String, AtomicLong> idGenerators = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(InMemoryStorage.class.getName());

    @Value("${storage.init.file.path}")
    private String initFilePath;

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing storage with file: " + initFilePath);
        try {
            loadDataFromCsv();
        } catch (IOException | CsvException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize storage from CSV file", e);
        }
    }

    public Map<Long, Object> getNamespace(String namespace) {
        return storage.computeIfAbsent(namespace, k -> new HashMap<>());
    }

    public long generateId(String namespace) {
        return idGenerators.computeIfAbsent(namespace, k -> new AtomicLong()).incrementAndGet();
    }

    public void clear() {
        storage.clear();
        idGenerators.clear();
    }

    private void loadDataFromCsv() throws IOException, CsvException {
        ClassPathResource resource = new ClassPathResource(initFilePath);
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> lines = reader.readAll();
            for (String[] line : lines) {
                String type = line[0];
                switch (type) {
                    case "trainee" -> {
                        Trainee trainee = new Trainee();
                        trainee.setId(Long.parseLong(line[1]));
                        trainee.setDateOfBirth(new Date(Long.parseLong(line[2])));
                        trainee.setAddress(line[3]);
                        getNamespace("trainees").put(trainee.getId(), trainee);
                        LOGGER.log(Level.INFO, "Loaded trainee: {0}", trainee);
                    }
                    case "trainer" -> {
                        Trainer trainer = new Trainer();
                        trainer.setId(Long.parseLong(line[1]));
                        trainer.setSpecialization(line[2]);
                        getNamespace("trainers").put(trainer.getId(), trainer);
                        LOGGER.log(Level.INFO, "Loaded trainer: {0}", trainer);
                    }
                    case "training" -> {
                        Training training = new Training();
                        training.setId(Long.parseLong(line[1]));
                        training.setTrainingName(line[2]);
                        training.setTrainingType(line[3]);
                        training.setTrainingDate(new Date(Long.parseLong(line[4])));
                        training.setTrainingDuration(Integer.parseInt(line[5]));
                        training.setTrainee((Trainee) getNamespace("trainees").get(Long.parseLong(line[6])));
                        training.setTrainer((Trainer) getNamespace("trainers").get(Long.parseLong(line[7])));
                        getNamespace("trainings").put(training.getId(), training);
                        LOGGER.log(Level.INFO, "Loaded training: {0}", training);
                    }
                    case "trainingType" -> {
                        TrainingType trainingType = new TrainingType();
                        trainingType.setId(Long.parseLong(line[1]));
                        trainingType.setTrainingTypeName(line[2]);
                        getNamespace("trainingTypes").put(trainingType.getId(), trainingType);
                        LOGGER.log(Level.INFO, "Loaded training type: {0}", trainingType);
                    }
                    default -> LOGGER.log(Level.WARNING, "Unknown type: {0}", type);
                }
            }
            LOGGER.log(Level.INFO, "Loaded data from CSV file: {0}", initFilePath);
        }
    }
}
