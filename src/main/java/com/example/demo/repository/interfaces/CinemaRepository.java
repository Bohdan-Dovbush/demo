package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Cinema;

import java.util.Optional;

public interface CinemaRepository extends MainRepository<Cinema> {

    Optional<Cinema> findWithImagesById(long id);
    Optional<Cinema> findWithHallsById(long id);
    Optional<Cinema> findWithImagesAndHallsById(long id);
}
