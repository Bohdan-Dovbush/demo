package com.example.demo.controller.user;

import com.example.demo.service.interfaces.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/soon")
public class SoonController {

    private final FilmService filmService;

    @Autowired
    public SoonController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String soon(Model model) {
        model.addAttribute("filmList", filmService.findFutureFilm());
        return "account/soon";
    }
}
