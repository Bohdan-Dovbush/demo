package com.example.demo.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface MainRepository<T> {

    List<T> findAll();
    Optional<T> findById(long id);
    void delete(T object);
    void deleteById(Long id);
    void update(T object);
    void save(T object);
}
