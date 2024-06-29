package com.example.gymapp.repo;

import java.util.List;

public interface BaseDao<T> {
    void create(T entity);
    void update(T entity);
    T select(Long id);
    List<T> selectAll();
}