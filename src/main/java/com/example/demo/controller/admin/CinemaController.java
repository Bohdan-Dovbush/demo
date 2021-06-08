package com.example.demo.controller.admin;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

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
            model.addAttribute("cinema", optionalCinema.get());
            return "admin/editCinema";
        }
        return "redirect:/admin/cinema";

    }

    @PostMapping(value = "/addCinemaMainImage")
    @ResponseBody
    public String setCinemaMainImage(@RequestParam MultipartFile file, @RequestParam Long cinemaId) {
        return cinemaService.setMainImageToCinema(cinemaId, file);
    }

//    @PostMapping(value = "/addCinemaLogoImage")
//    @ResponseBody
//    public String setCinemaLogoImage(@RequestParam MultipartFile file, @RequestParam Long cinemaId) {
//        return cinemaService.setLogoImageToCinema(cinemaId, file);
//    }

    @PostMapping(value = "/addCinemaUpperBannerImage")
    public String setCinemaUpperBannerImage(@RequestParam MultipartFile file, @RequestParam Long cinemaId) {
        return cinemaService.setUpperBannerImageToCinema(cinemaId, file);
    }

    @PostMapping(value = "/addImageInCinema")
    @ResponseBody
    public String addImageInCinema(@RequestParam MultipartFile file, @RequestParam Long cinemaId) {
        return cinemaService.addImageToCinema(cinemaId, file).getImage();
    }

    @DeleteMapping("/deleteImageInCinema")
    @ResponseBody
    public boolean deleteImageInCinema(@RequestParam String cinemaImageName) {
        cinemaService.deleteCinemaImage(cinemaImageName);
        return true;
    }

    @RequestMapping(value = "/updateCinema")
    public String updateCinema(
            @RequestParam Long cinemaId,
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam(required = false) MultipartFile mainImageFile,
            @RequestParam(required = false) MultipartFile logoImageFile,
            @RequestParam(required = false) MultipartFile UpperBannerImageFile,
            @RequestParam(required = false) List<MultipartFile> cinemaImages,
            @RequestParam(required = false, name = "deletedImage") List<Long> deletedImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        cinemaService.updateCinema(cinemaId, cinemaName, cinemaDescription, cinemaRules,
                mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages, deletedImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/cinema";
    }

    @GetMapping("/createCinema")
    public String createCinema(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createCinema";
    }

    @PostMapping(value = "/saveCinema")
    public String saveCinema(@ModelAttribute(name = "cinema") Cinema cinema,
            @RequestParam("mainImageFile") MultipartFile multipartFile,
            RedirectAttributes ra) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        cinema.setLogoImage(fileName);
        Cinema savedCinema = cinemaService.save(cinema);

        String uploadDir = "./images/" + savedCinema.getCinemaId();
        Path uploadPath = Paths.get(uploadDir);

        if(Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        ra.addFlashAttribute("message", "The cinema has been saved successfully.");
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
