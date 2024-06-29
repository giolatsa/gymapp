package com.example.gymapp.service;

import java.util.List;

public interface BaseService<T> {
    void create(T entity);
    void update(T entity);
    T get(Long id);
    List<T> getAll();
}
