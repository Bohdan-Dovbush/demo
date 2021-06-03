package com.example.demo.service.interfaces;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.FilmImage;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmService extends MainService<Film>{

    Optional<Film> findImagesById(Long id);
    Optional<Film> findTypesById(Long id);
    Optional<Film> findImagesAndTypesById(Long id);
    List<Film> findAll();
    List<Film> findAllWithTypes();
    String addMainImage(Long id, MultipartFile file);
    String addActor(Long id);
    FilmImage addImageToFilm(Long id, MultipartFile file);
    void deleteFilmImage(String name);
    void updateFilm(Long id, String name, LocalDate filmYear, String description, Genre genres,
                    LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                    Type types, MultipartFile mainImage, List<Long> deletedImages,
                    List<MultipartFile> images, String trailerLink, Seo seo);
    void addFilm(String name, LocalDate filmYear, String description, Genre genres,
                 LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                 Type types, MultipartFile mainImage,
                 List<MultipartFile> images, String trailerLink, Seo seo);
}
