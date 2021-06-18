package com.example.demo.controller.user;

import com.example.demo.service.interfaces.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/poster")
public class PosterController {

    private final FilmService filmService;

    @Autowired
    public PosterController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String poster(Model model) {
        model.addAttribute("filmList", filmService.findCurrentFilm());
        return "account/poster";
    }
}
