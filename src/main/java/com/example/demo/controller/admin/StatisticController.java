package com.example.demo.controller.admin;

import com.example.demo.service.interfaces.FilmService;
import com.example.demo.service.interfaces.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/statistic")
public class StatisticController {

    private final UserEntityService userService;
    private final FilmService filmService;

    @Autowired
    public StatisticController(UserEntityService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }

    @GetMapping
    public String getStatistics(Model model) {

        model.addAttribute("users_count", userService.findCountUser());

        model.addAttribute("total_film", filmService.findTotalCountFilm());
        model.addAttribute("current_film", filmService.findCurrentCountFilm());
        model.addAttribute("future_film", filmService.findFutureCountFilm());

        return "admin/dashboard";
    }
}
