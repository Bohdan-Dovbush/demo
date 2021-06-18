package com.example.demo.service.impl;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.film.Seo;
import com.example.demo.repository.FilmRepository;
import com.example.demo.service.interfaces.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl extends ImageServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Film> findImagesById(Long id) {
        return filmRepository.findImagesById(id);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public String addActor(Long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if(optionalFilm.isPresent() ) {
            Film film = optionalFilm.get();
            Actor actor = new Actor();
            film.addFilmActor(actor);
            filmRepository.saveAndFlush(film);
            return String.valueOf(actor);
        }
        return null;
    }

    @Override
    public void updateFilm(Long id, String name, String filmYear, String description, Genre genres,
                           LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                           Type types, MultipartFile mainImage,
                           MultipartFile[] images, String trailerLink, Seo seo) {
        findImagesById(id).ifPresent(film -> {
            film.setName(name);
            film.setDescription(description);
            film.setFilmYear(filmYear);
            film.setLanguage(language);
            film.setFilmActor(actors);
            film.setType(types);
            film.setGenre(genres);
            film.setTrailerLink(trailerLink);
            film.setDateRelease(dateRealise);
            film.setDateFinish(dateFinish);
            checkFilmImage(film, mainImage, images);
            if (!film.getSeo().equals(seo)){
                film.setSeo(seo);
            }
            filmRepository.save(film);
        });
    }

    @Override
    public void addFilm(String name, String filmYear, String description, Genre genres,
                        LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                        Type types, MultipartFile mainImage,
                        MultipartFile[] images, String trailerLink, Seo seo) {
        Film film = new Film();
        film.setName(name);
        film.setDescription(description);
        film.setFilmYear(filmYear);
        film.setLanguage(language);
        film.setFilmActor(actors);
        film.setType(types);
        film.setGenre(genres);
        film.setTrailerLink(trailerLink);
        film.setSeo(seo);
        film.setDateRelease(dateRealise);
        film.setDateFinish(dateFinish);
        checkFilmImage(film, mainImage, images);
        filmRepository.save(film);
    }

    @Override
    public void deleteById(Long id) {
        Film film = new Film();
        deleteImage(film.getMainImage());
        deleteImageSet(film.getFilmImages());
        filmRepository.deleteById(id);
    }

    @Override
    public Integer findTotalCountFilm() {
        return filmRepository.findTotalCountFilm();
    }

    @Override
    public Integer findCurrentCountFilm() {
        return filmRepository.findCurrentCountFilm();
    }

    @Override
    public Integer findFutureCountFilm() {
        return filmRepository.findFutureCountFilm();
    }

    @Override
    public List<Film> findCurrentFilm() {
        return filmRepository.findCurrentFilm();
    }

    @Override
    public List<Film> findFutureFilm() {
        return filmRepository.findFutureFilm();
    }

    void checkFilmImage(Film film, MultipartFile mainImage, MultipartFile[] filmImages) {
        if (!(mainImage == null) && !(mainImage.isEmpty())) {
            deleteImage(film.getMainImage());
            film.setMainImage(saveImage(mainImage));
        }
        if (!(filmImages == null) && !(filmImages.length == 0)) {
            if(!(filmImages[0] == null) && !(filmImages[0].isEmpty())) {
                deleteImageSet(film.getFilmImages());
                film.setFilmImages(saveImageArray(filmImages));
            }
        }
    }
}
