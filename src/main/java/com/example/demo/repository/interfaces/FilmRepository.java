package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends MainRepository<Film> {

    Optional<Film> findImagesById(Long id);
    Optional<Film> findTypesById(Long id);
    Optional<Film> findImagesAndTypesById(Long id);

    List<Film> findAllWithTypes();
}
