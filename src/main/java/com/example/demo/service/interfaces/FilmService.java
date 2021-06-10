package com.example.demo.service.interfaces;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.film.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmService{

    Optional<Film> findImagesById(Long id);
    List<Film> findAll();
    String addActor(Long id);
    void updateFilm(Long id, String name, LocalDate filmYear, String description, Genre genres,
                    LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                    Type types, MultipartFile mainImage,
                    MultipartFile[] images, String trailerLink, Seo seo);
    void addFilm(String name, LocalDate filmYear, String description, Genre genres,
                 LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                 Type types, MultipartFile mainImage,
                 MultipartFile[] images, String trailerLink, Seo seo);
    void deleteById(Long id);
}
