package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Film;

import java.util.Optional;

public interface FilmRepository extends MainRepository<Film> {

    Optional<Film> findImagesById(Long id);
}
