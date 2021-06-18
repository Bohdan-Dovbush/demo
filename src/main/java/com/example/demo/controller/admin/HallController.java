package com.example.demo.controller.admin;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.CinemaService;
import com.example.demo.service.interfaces.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("admin/hall")
public class HallController {

    private final HallService hallService;
    private final CinemaService cinemaService;

    @Autowired
    public HallController(HallService hallService, CinemaService cinemaService) {
        this.hallService = hallService;
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public String hall(Model model) {
        model.addAttribute("hallList", hallService.findAll());
        model.addAttribute("cinemaList", cinemaService.findAll());
        return "admin/hall/adminHall";
    }

    @GetMapping("/create")
    public ModelAndView createHall(ModelMap model, @RequestParam Long cinemaId) {
        model.addAttribute("cinemaId", cinemaId);
        return new ModelAndView("admin/hall/createHall", model);
    }

    @GetMapping(value = "/edit")
    public ModelAndView editHall(ModelMap model,
                           @RequestParam Long hallId) {
        Optional<Hall> optionalHall = hallService.findByHallImagesAndSeances(hallId);
        if (optionalHall.isPresent()) {
            model.addAttribute("hall", optionalHall.get());
            return new ModelAndView("admin/hall/editHall", model);
        }
        return new ModelAndView("redirect:/admin/cinema/edit", model);
    }

    @GetMapping(value = "/delete")
    public String deleteHall(@RequestParam Long hallId) {
        hallService.deleteById(hallId);
        return "redirect:/admin/hall";
    }

    @PostMapping(value = "/update")
    public ModelAndView updateHall(ModelMap model,
                                   @RequestParam Long cinemaId,
                                   @RequestParam Long hallId,
                                   @RequestParam String hallName,
                                   @RequestParam String hallDescription,
                                   @RequestParam(required = false) MultipartFile hallSchemaImage,
                                   @RequestParam(required = false) MultipartFile hallBannerImage,
                                   @RequestParam(required = false) MultipartFile[] hallImages,
                                   @RequestParam String seoURL,
                                   @RequestParam String seoTitle,
                                   @RequestParam String seoKeyWords,
                                   @RequestParam String seoDescription) {

        model.addAttribute("cinemaId", cinemaId);
        hallService.updateHall(hallId, hallName, hallDescription, hallSchemaImage,
                hallBannerImage, hallImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/cinema/edit", model);
    }

    @PostMapping("/save")
    public ModelAndView saveHall(ModelMap model,
                                 @RequestParam String hallName,
                                 @RequestParam String hallDescription,
                                 @RequestParam Long cinemaId,
                                 @RequestParam(required = false) MultipartFile hallSchemaImage,
                                 @RequestParam(required = false) MultipartFile hallBannerImage,
                                 @RequestParam(required = false) MultipartFile[] hallImages,
                                 @RequestParam String seoURL,
                                 @RequestParam String seoTitle,
                                 @RequestParam String seoKeyWords,
                                 @RequestParam String seoDescription
    ) {
        model.addAttribute("cinemaId", cinemaId);
        hallService.createHall(hallName, hallDescription, cinemaId, hallSchemaImage, hallBannerImage, hallImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/cinema/edit", model);
    }
}
