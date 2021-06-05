package com.example.demo.controller.admin;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import com.example.demo.entity.film.Actor;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.FilmService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("admin/film")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String films(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("films", filmService.findAll());
        return "admin/adminFilm";
    }

    @GetMapping("/addFilm")
    public String addFilm(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createFilm";
    }

    @PostMapping(value = "/saveFilm")
    public String saveMovie(
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
            @RequestParam(required = false) List<MultipartFile> images,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        filmService.addFilm(filmName, filmYear, description, genres, dateRealise, dateFinish, language, actors, type,
                mainImage, images, trailerLink, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/film";
    }

    @PostMapping(value = "/editFilm")
    public String editMovie(Model model, @RequestParam Long id) {
        filmService.findImagesById(id).ifPresent(film -> {
            model.addAttribute("userName", getUserName());
            model.addAttribute("film", film);
            model.addAttribute("filmImage", film.getMainImage());
        });
        return "admin/editFilm";
    }

    @RequestMapping("/addMainImage")
    @ResponseBody
    public String addMainImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        return filmService.addMainImage(id, file);
    }

    @RequestMapping(value = "/updateFilm")
    public String updateFilm(
            @RequestParam LocalDate filmYear,
            @RequestParam Genre genres,
            @RequestParam LocalDate dateRealise,
            @RequestParam LocalDate dateFinish,
            @RequestParam Language language,
            @RequestParam List<Actor> actors,
            @RequestParam Type types,
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false, name = "mainImage") MultipartFile mainImage,
            @RequestParam(required = false, name = "deletedImages") List<Long> deletedImages,
            @RequestParam(required = false) List<MultipartFile> images,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        filmService.updateFilm(id, name, filmYear, description, genres, dateRealise, dateFinish, language,
                actors, types, mainImage, deletedImages, images, trailerLink, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/adminFilm";
    }

    @GetMapping("/deleteFilm")
    @ResponseBody
    public void deleteFilm(@RequestParam Long id) {
        filmService.deleteById(id);
    }

    @PostMapping(value = "/addFilmImage")
    @ResponseBody
    public String addFilmImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        return filmService.addImageToFilm(id, file).getImage();
    }

    @GetMapping("/deleteImageInMove")
    @ResponseBody
    public Boolean deleteImageInMove(@RequestParam String image) {
        filmService.deleteFilmImage(image);
        return true;
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
