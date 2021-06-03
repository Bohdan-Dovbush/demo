package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Hall;

import java.util.Optional;

public interface HallRepository extends MainRepository<Hall>{

    Optional<Hall> findByHallImages(Long id);
    Optional<Hall> findByHallImagesAndSeances(Long id);
}
