package com.example.demo.controller.admin;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

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
        model.addAttribute("userName", getUserName());
        model.addAttribute("cinemas", cinemaService.findAll());
        return "admin/adminCinemas";
    }

    @GetMapping(value = "/edit", params = {"cinemaId"})
    public String editCinema(Model model, @RequestParam Long cinemaId) {
        Optional<Cinema> optionalCinema = cinemaService.findWithImagesAndHallsById(cinemaId);
        if (optionalCinema.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("cinema", optionalCinema.get().getHalls());
            return "admin/editCinema";
        }
        return "redirect:/admin/cinema";

    }

    @RequestMapping(value = "/updateCinema")
    public String updateCinema(
            @RequestParam Long cinemaId,
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam Boolean isActive,
            @RequestParam(required = false) MultipartFile mainImageFile,
            @RequestParam(required = false) MultipartFile logoImageFile,
            @RequestParam(required = false) MultipartFile UpperBannerImageFile,
            @RequestParam(required = false) MultipartFile[] cinemaImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        cinemaService.updateCinema(cinemaId, cinemaName, cinemaDescription, cinemaRules, isActive,
                mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/cinema";
    }

    @GetMapping("/createCinema")
    public String createCinema(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createCinema";
    }

    @PostMapping(value = "/saveCinema")
    public String saveCinema(
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) MultipartFile mainImageFile,
            @RequestParam(required = false) MultipartFile logoImageFile,
            @RequestParam(required = false) MultipartFile UpperBannerImageFile,
            @RequestParam(required = false) MultipartFile[] cinemaImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {
        String logo = cinemaService.saveImage(logoImageFile);
        String main = cinemaService.saveImage(mainImageFile);
        String banner = cinemaService.saveImage(UpperBannerImageFile);
        Set<String> images = cinemaService.saveImageArray(cinemaImages);

        Cinema cinema = new Cinema(images, cinemaName, isActive, cinemaDescription, cinemaRules, main, logo, banner,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        cinemaService.save(cinema);
        return "redirect:/admin/cinema";
    }

    @GetMapping(value = "/delete", params = {"cinemaId"})
    public String deleteCinema(@RequestParam Long cinemaId) {
        cinemaService.deleteById(cinemaId);
        return "redirect:/admin/cinema";
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
