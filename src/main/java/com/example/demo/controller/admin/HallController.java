package com.example.demo.controller.admin;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.HallService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/hall")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping(value = "/editHall", params = {"cinemaId", "hallId"})
    public String editHall(Model model,
                           @RequestParam Long cinemaId,
                           @RequestParam Long hallId) {
        Optional<Hall> optionalHall = hallService.findByHallImagesAndSeances(hallId);
        if (optionalHall.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("cinemaId", cinemaId);
            model.addAttribute("hall", optionalHall.get());
            return "admin/editHall";
        }
        return "redirect:/admin/cinema";
    }

    @RequestMapping(value = "/updateHall")
    public ModelAndView updateHall(ModelMap model,
                                   @RequestParam Long cinemaId,
                                   @RequestParam Long hallId,
                                   @RequestParam String hallName,
                                   @RequestParam String hallDescription,
                                   @RequestParam(required = false) MultipartFile hallSchemaImage,
                                   @RequestParam(required = false) MultipartFile hallBannerImage,
                                   @RequestParam(required = false) List<MultipartFile> hallImages,
                                   @RequestParam(required = false, name = "deletedImage") List<Long> deletedImages,
                                   @RequestParam String seoURL,
                                   @RequestParam String seoTitle,
                                   @RequestParam String seoKeyWords,
                                   @RequestParam String seoDescription) {

        model.addAttribute("cinemaId", cinemaId);
        hallService.updateHall(hallId, hallName, hallDescription, hallSchemaImage,
                hallBannerImage, hallImages, deletedImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/editCinema", model);

//        return "redirect:/admin/cinema";
    }

    @GetMapping("/createHall")
    public ModelAndView createHall(ModelMap model, @RequestParam Long cinemaId) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("cinemaId", cinemaId);
        return new ModelAndView("admin/createHall", model);
    }

    @RequestMapping("/saveHall")
    public ModelAndView saveHall(ModelMap model,
                                 @RequestParam String hallName,
                                 @RequestParam String hallDescription,
                                 @RequestParam Long cinemaId,
                                 @RequestParam(required = false) MultipartFile hallSchemaImage,
                                 @RequestParam(required = false) MultipartFile hallBannerImage,
                                 @RequestParam(required = false) List<MultipartFile> hallImages,
                                 @RequestParam String seoURL,
                                 @RequestParam String seoTitle,
                                 @RequestParam String seoKeyWords,
                                 @RequestParam String seoDescription
    ) {
        model.addAttribute("cinemaId",cinemaId);
        hallService.createHall(hallName, hallDescription, cinemaId, hallSchemaImage, hallBannerImage, hallImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/editCinema", model);
    }

    @GetMapping("/deleteHall")
    @ResponseBody
    public void deleteHall(@RequestParam Long id) {
        hallService.deleteById(id);
    }


    @RequestMapping("/addSchemaImage")
    @ResponseBody
    public String addSchemaImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        return hallService.addSchemaImage(id, file);
    }

    @RequestMapping("/addBannerImage")
    @ResponseBody
    public String addBannerImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        return hallService.addBannerImage(id, file);
    }

    @RequestMapping("/addImageToHall")
    @ResponseBody
    public String addImageToHall(@RequestParam MultipartFile file, @RequestParam Long id) {
        return hallService.addHallImage(id, file).getImage();
    }

    @RequestMapping("/deleteImageInHall")
    @ResponseBody
    public Boolean deleteImageInHall(@RequestParam Long id) {
        hallService.deleteHallImage(id);
        return true;
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
