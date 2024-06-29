package com.example.gymapp;


import com.example.gymapp.config.AppConfig;
import com.example.gymapp.controller.TrainingFacade;
import com.example.gymapp.model.Trainee;
import com.example.gymapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GymappApplication {
    private static final Logger LOGGER = Logger.getLogger(GymappApplication.class.getName());

    private static ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        GymappApplication.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {



        LOGGER.log(Level.INFO, "Application started");

        // Initialize Spring context
        context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Get training facade from application context
        TrainingFacade facade = getContext().getBean(TrainingFacade.class);

        //check if data was loaded from file
        LOGGER.log(Level.INFO, "Users: {0}", facade.getAllUsers().size());
        LOGGER.log(Level.INFO, "Trainees: {0}", facade.getAllTrainees().size());
        LOGGER.log(Level.INFO, "Trainers: {0}", facade.getAllTrainers().size());

        //Create new trainee
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setIsActive(true);

        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setAddress("123 Main St");
        trainee.setDateOfBirth(new Date());
        LOGGER.log(Level.INFO, "Created Trainee: {0}", facade.createTrainee(trainee));

        LOGGER.log(Level.INFO, "Application stopped");
    }
}