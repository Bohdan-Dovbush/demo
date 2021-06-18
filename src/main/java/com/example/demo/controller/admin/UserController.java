package com.example.demo.controller.admin;

import com.example.demo.entity.enums.Gender;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.interfaces.DetailsService;
import com.example.demo.service.interfaces.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private final UserEntityService userEntityService;
    private final DetailsService detailsService;

    @Autowired
    public UserController(UserEntityService userEntityService, DetailsService detailsService) {
        this.userEntityService = userEntityService;
        this.detailsService = detailsService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("detailsList", detailsService.findAll());
        return "admin/user/adminUsers";
    }

    @GetMapping(value = "/edit")
    public ModelAndView editUser(ModelMap model,
                                 @RequestParam Long userId,
                                 @RequestParam(required = false) Long detailsId) {
        ModelAndView result;
        Optional<UserEntity> optionalUserEntity = userEntityService.findDetailsAndContactById(userId);
            if (optionalUserEntity.isPresent()) {
                model.addAttribute("detailsId", detailsId);
                model.addAttribute("user", optionalUserEntity.get());
                result = new ModelAndView("admin/user/editUser", model);
            } else {
                result = new ModelAndView("redirect:/admin/user");
            }
        return result;
    }

    @PostMapping("/update")
    public String updateUser(Model model,
                                   @RequestParam Long detailsId,
                                   @RequestParam Language language,
                                   @RequestParam Gender genders,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
                                   @RequestParam(required = false) MultipartFile mainImage) {
        detailsService.updateDetails(detailsId, birthday, mainImage, language, genders);
        return "redirect:/admin/user";
    }
}
