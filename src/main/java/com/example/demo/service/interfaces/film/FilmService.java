package com.example.demo.service.interfaces.film;

import com.example.demo.entity.film.Film;

import java.util.List;

public interface FilmService {

    List<Film> getAllFilms();

    Film getFilmByID(Long id);

    Film updateFilm(Film film, Long id);

    Film addFilm(Film film);

    void deleteFilmByID(Long id);
}
