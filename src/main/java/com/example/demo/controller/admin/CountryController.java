package com.example.demo.controller.admin;

import com.example.demo.entity.address.Country;
import com.example.demo.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String country(Model model) {
        model.addAttribute("countryList", countryService.findAll());
        return "admin/country/adminCountry";
    }

    @GetMapping("/create")
    public String createCountry() {
        return "admin/country/createCountry";
    }

    @GetMapping(value = "/edit", params = {"countryId"})
    public String editCountry(Model model, @RequestParam Long countryId) {
        Optional<Country> optionalCountry = countryService.findAllContactById(countryId);
        if (optionalCountry.isPresent()) {
            model.addAttribute("country", optionalCountry.get());
            return "admin/country/editCountry";
        }
        return "redirect:/admin/country";
    }

    @PostMapping("/save")
    public String saveCountry(@RequestParam String name) {
        countryService.createCountry(name);
        return "redirect:/admin/country";
    }

    @GetMapping(value = "/delete", params = {"countryId"})
    public String deleteCountry(@RequestParam Long countryId) {
        countryService.deleteById(countryId);
        return "redirect:/admin/country";
    }
}
