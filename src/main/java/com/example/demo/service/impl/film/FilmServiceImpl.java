package com.example.demo.service.impl.film;

import com.example.demo.entity.film.Film;
import com.example.demo.exeption.FilmNotFoundException;
import com.example.demo.repository.interfaces.FilmRepository;
import com.example.demo.service.interfaces.film.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilmByID(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException(id));
    }

    @Override
    public Film updateFilm(Film film, Long id) {
        return null;
    }

    @Override
    public Film addFilm(Film film) {
        return null;
    }

    @Override
    public void deleteFilmByID(Long id) {
        filmRepository.deleteById(id);
    }
}
