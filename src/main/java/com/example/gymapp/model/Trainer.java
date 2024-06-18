package com.example.gymapp.model;

public class Trainer {

    private Long id;
    private String specialization;

    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                ", user=" + user +
                '}';
    }
}