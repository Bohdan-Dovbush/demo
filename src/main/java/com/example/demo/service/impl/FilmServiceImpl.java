package com.example.demo.service.impl;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.FilmImage;
import com.example.demo.repository.interfaces.FilmImageRepository;
import com.example.demo.repository.interfaces.FilmRepository;
import com.example.demo.service.interfaces.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl extends MainServiceImpl<Film> implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmImageRepository filmImageRepository;

    public FilmServiceImpl(FilmRepository filmRepository, FilmImageRepository filmImageRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
        this.filmImageRepository = filmImageRepository;
    }

    @Override
    public Optional<Film> findImagesById(Long id) {
        return filmRepository.findImagesById(id);
    }

    @Override
    public Optional<Film> findTypesById(Long id) {
        return filmRepository.findTypesById(id);
    }

    @Override
    public Optional<Film> findImagesAndTypesById(Long id) {
        return filmRepository.findImagesAndTypesById(id);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> findAllWithTypes() {
        return filmRepository.findAllWithTypes();
    }

    @Override
    public String addMainImage(Long id, MultipartFile file) {
        Optional<Film> optionalMovie = filmRepository.findById(id);
        if (optionalMovie.isPresent() && !file.isEmpty()) {
            Film film = optionalMovie.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName, file)) {
                film.setMainImage(fileName);
                update(film);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String addActor(Long id) {
        Optional<Film> optionalMovie = filmRepository.findById(id);
        if(optionalMovie.isPresent() ) {
            Film film = optionalMovie.get();
            Actor actor = new Actor();
            film.addFilmActor(actor);
            update(film);
            return String.valueOf(actor);
        }
        return null;
    }

    @Override
    public FilmImage addImageToFilm(Long id, MultipartFile file) {
        Optional<Film> optionalMovie = filmRepository.findImagesById(id);
        if (optionalMovie.isPresent() && !file.isEmpty()) {
            Film film = optionalMovie.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName, file)) {
                FilmImage image = new FilmImage(fileName);
                film.addFilmImage(image);
                update(film);
                return image;
            }
        }
        return null;
    }

    @Override
    public void deleteFilmImage(String name) {
        filmImageRepository.deleteByImageName(name);
    }

    @Override
    public void updateFilm(Long id, String name, LocalDate filmYear, String description, Genre genres,
                           LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                           Type types, MultipartFile mainImage, List<Long> deletedImages,
                           List<MultipartFile> images, String trailerLink, Seo seo) {
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
            filmRepository.update(film);
            if (deletedImages != null){
                filmImageRepository.deleteByListOfId(deletedImages);
            }
        });
    }

    @Override
    public void addFilm(String name, LocalDate filmYear, String description, Genre genres,
                        LocalDate dateRealise, LocalDate dateFinish, Language language, List<Actor> actors,
                        Type types, MultipartFile mainImage,
                        List<MultipartFile> images, String trailerLink, Seo seo) {
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

    void checkFilmImage(Film film, MultipartFile mainImage, List<MultipartFile> cinemaImages) {
        if (!mainImage.isEmpty()){
            film.setMainImage(saveImageAndGetName(mainImage));
        }
        if (!cinemaImages.isEmpty()){
            cinemaImages.forEach(image -> film.addFilmImage(new FilmImage(saveImageAndGetName(image))));
        }
    }
}
