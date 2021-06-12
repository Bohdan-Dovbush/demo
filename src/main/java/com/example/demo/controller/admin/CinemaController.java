package com.example.demo.controller.admin;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public String cinemas(Model model) {
        model.addAttribute("cinemas", cinemaService.findAll());
        return "admin/cinema/adminCinemas";
    }

    @GetMapping("/createCinema")
    public String createCinema() {
        return "admin/cinema/createCinema";
    }

    @GetMapping(value = "/edit", params = {"cinemaId"})
    public String editCinema(Model model, @RequestParam Long cinemaId) {
        Optional<Cinema> optionalCinema = cinemaService.findWithImagesAndHallsById(cinemaId);
        if (optionalCinema.isPresent()) {
            model.addAttribute("cinema", optionalCinema.get());
            return "admin/cinema/editCinema";
        }
        return "redirect:/admin/cinema";

    }

    @GetMapping(value = "/delete", params = {"cinemaId"})
    public String deleteCinema(@RequestParam Long cinemaId) {
        cinemaService.deleteById(cinemaId);
        return "redirect:/admin/cinema";
    }

    @PostMapping(value = "/updateCinema")
    public String updateCinema(
            @RequestParam Long cinemaId,
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam Boolean isActive,
            @RequestParam("mainImageFile") MultipartFile mainImageFile,
            @RequestParam("logoImageFile") MultipartFile logoImageFile,
            @RequestParam("UpperBannerImageFile") MultipartFile UpperBannerImageFile,
            @RequestParam("cinemaImages") MultipartFile[] cinemaImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        cinemaService.updateCinema(cinemaId, cinemaName, cinemaDescription, cinemaRules, isActive,
                mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/cinema";
    }

    @PostMapping(value = "/saveCinema")
    public String saveCinema(
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam Boolean isActive,
            @RequestParam("mainImageFile") MultipartFile mainImageFile,
            @RequestParam("logoImageFile") MultipartFile logoImageFile,
            @RequestParam("UpperBannerImageFile") MultipartFile UpperBannerImageFile,
            @RequestParam("cinemaImages") MultipartFile[] cinemaImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        cinemaService.addCinema(cinemaName, cinemaDescription, cinemaRules, isActive, mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/cinema";
    }
}
