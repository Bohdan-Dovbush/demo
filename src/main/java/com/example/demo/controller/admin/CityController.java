package com.example.demo.controller.admin;

import com.example.demo.entity.address.City;
import com.example.demo.service.interfaces.СityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/city")
public class CityController {

    private final СityService cityService;

    @Autowired
    public CityController(СityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String city(Model model) {
        model.addAttribute("cityList", cityService.findAll());
        return "admin/city/adminCity";
    }

    @GetMapping("/create")
    public ModelAndView createCity(ModelMap model, @RequestParam Long countryId) {
        model.addAttribute("countryId", countryId);
        return new ModelAndView("admin/city/createCity", model);
    }

    @GetMapping(value = "/edit")
    public ModelAndView editAddress(ModelMap model,
                                    @RequestParam Long cityId,
                                    @RequestParam Long countryId) {
        Optional<City> optionalCity = cityService.findCityAndAddressById(cityId);
        if (optionalCity.isPresent()) {
            model.addAttribute("countryId", countryId);
            model.addAttribute("city", optionalCity.get());
            return new ModelAndView("admin/country/editCountry", model);
        }
        return new ModelAndView("redirect:/admin/city/edit", model);
    }

    @GetMapping(value = "/delete")
    public String deleteCity(@RequestParam Long cityId) {
        cityService.deleteById(cityId);
        return "redirect:/admin/city";
    }

    @PostMapping(value = "/save")
    public ModelAndView saveCity(ModelMap model,
                                    @RequestParam Long countryId,
                                    @RequestParam String name) {
        model.addAttribute("countryId", countryId);
        cityService.createCity(name, countryId);
        return new ModelAndView("redirect:/admin/country/edit", model);
    }
}
