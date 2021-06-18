package com.example.demo.controller.admin;

import com.example.demo.entity.film.Seance;
import com.example.demo.service.interfaces.FilmService;
import com.example.demo.service.interfaces.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin/seance")
public class SeanceController {

    private final SeanceService seanceService;
    private final FilmService filmService;

    @Autowired
    public SeanceController(SeanceService seanceService, FilmService filmService) {
        this.seanceService = seanceService;
        this.filmService = filmService;
    }

    @GetMapping
    public String seance(Model model) {
        model.addAttribute("seanceList", seanceService.findAll());
        return "admin/seance/adminSeances";
    }

    @GetMapping("/create")
    public ModelAndView createSeance(ModelMap model, @RequestParam Long hallId) {
        model.addAttribute("hallId", hallId);
        model.addAttribute("films", filmService.findAll());
        return new ModelAndView("admin/seance/createSeance", model);
    }

    @GetMapping(value = "/edit", params = {"seanceId", "hallId"})
    public String editSeance(Model model,
                              @RequestParam Long hallId,
                              @RequestParam Long seanceId) {
        Optional<Seance> optionalSession = seanceService.findWithFilmById(seanceId);
        if (optionalSession.isPresent()) {
            Seance seance = optionalSession.get();
            model.addAttribute("seances", seance);
            model.addAttribute("hallId", hallId);
            model.addAttribute("films", filmService.findAll());
            return "admin/seance/editSeance";
        }
        return "redirect:/admin/seance";
    }

    @GetMapping(value = "/delete", params = {"seanceId"})
    public String deleteSeance(@RequestParam Long seanceId) {
        seanceService.deleteById(seanceId);
        return "redirect:/admin/seance";
    }

    @PostMapping("/update")
    public ModelAndView updateSeance(ModelMap model,
                                      @RequestParam Long hallId,
                                      @RequestParam Long seanceId,
                                      @RequestParam Long filmId,
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime seanceTime) {
        model.addAttribute("hallId",hallId);
        seanceService.updateSeance(seanceId, filmId, seanceTime);
        return new ModelAndView("redirect:/admin/hall/edit", model);
    }

    @PostMapping("/save")
    public ModelAndView saveSeance(ModelMap model,
                                    @RequestParam Long filmId,
                                    @RequestParam Long hallId,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime seanceTime) {
        model.addAttribute("hallId",hallId);
        model.addAttribute("filmId", filmId);
        seanceService.createSeance(hallId, filmId, seanceTime);
        return new ModelAndView("redirect:/admin/hall/edit", model);
    }
}
