package com.example.demo.controller.admin;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/film")
public class FilmController {

    FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String films(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "admin/film/adminFilm";
    }

    @GetMapping("/addFilm")
    public String addFilm() {
        return "admin/film/createFilm";
    }

    @PostMapping(value = "/saveFilm")
    public String saveFilm(
            @RequestParam String filmName,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate filmYear,
            @RequestParam String description,
            @RequestParam Genre genres,
            @RequestParam Type type,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRealise,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinish,
            @RequestParam Language language,
            @RequestParam(required = false) List<Actor> actors,
            @RequestParam(required = false) MultipartFile mainImage,
            @RequestParam(required = false) MultipartFile[] images,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        filmService.addFilm(filmName, filmYear, description, genres, dateRealise, dateFinish, language, actors, type,
                mainImage, images, trailerLink, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/film";
    }

    @GetMapping(value = "/editFilm", params = {"filmId"})
    public String editFilm(Model model, @RequestParam Long filmId) {
        Optional<Film> filmOptional = filmService.findImagesById(filmId);
        if(filmOptional.isPresent()) {
            model.addAttribute("film", filmOptional.get());
            return "admin/film/editFilm";
        }
        return "redirect:/admin/film";
    }

    @RequestMapping(value = "/updateFilm")
    public String updateFilm(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate filmYear,
            @RequestParam Genre genres,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRealise,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinish,
            @RequestParam Language language,
            @RequestParam(required = false) List<Actor> actors,
            @RequestParam(name = "types") Type types,
            @RequestParam Long filmId,
            @RequestParam(name = "name") String name,
            @RequestParam String description,
            @RequestParam(required = false, name = "mainImage") MultipartFile mainImage,
            @RequestParam(required = false) MultipartFile[] filmImages,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        filmService.updateFilm(filmId, name, filmYear, description, genres, dateRealise, dateFinish, language,
                actors, types, mainImage, filmImages, trailerLink, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/film";
    }

    @GetMapping(value = "/deleteFilm", params = {"filmId"})
    public String deleteFilm(@RequestParam Long filmId) {
        filmService.deleteById(filmId);
        return "redirect:/admin/film";
    }
}
