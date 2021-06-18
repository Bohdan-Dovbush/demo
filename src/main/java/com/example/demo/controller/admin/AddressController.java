package com.example.demo.controller.admin;

import com.example.demo.entity.address.Address;
import com.example.demo.service.interfaces.AddressService;
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
@RequestMapping("/admin/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String address(Model model) {
        model.addAttribute("addressList", addressService.findAll());
        return "admin/address/adminAddress";
    }

    @GetMapping("/create")
    public ModelAndView createAddress(ModelMap model, @RequestParam Long cityId) {
        model.addAttribute("cityId", cityId);
        return new ModelAndView("admin/address/createAddress", model);
    }

    @GetMapping(value = "/edit")
    public ModelAndView editAddress(ModelMap model,
                                 @RequestParam Long cityId,
                                 @RequestParam Long addressId) {
        Optional<Address> optionalAddress = addressService.findById(addressId);
        if (optionalAddress.isPresent()) {
            model.addAttribute("cityId", cityId);
            model.addAttribute("address", optionalAddress.get());
            return new ModelAndView("admin/address/editAddress", model);
        }
        return new ModelAndView("redirect:/admin/city/edit", model);
    }

    @GetMapping(value = "/delete", params = {"addressId"})
    public String deleteAddress(@RequestParam Long addressId) {
        addressService.deleteById(addressId);
        return "redirect:/admin/address";
    }

    @PostMapping(value = "/update")
    public ModelAndView updateAddress(ModelMap model,
                                   @RequestParam Long cityId,
                                   @RequestParam Long addressId,
                                   @RequestParam String street,
                                   @RequestParam String region,
                                   @RequestParam Integer postalCode) {
        model.addAttribute("cityId", cityId);
        addressService.updateAddress(addressId, street, postalCode, region);
        return new ModelAndView("redirect:/admin/city/edit", model);
    }

    @PostMapping(value = "/save")
    public ModelAndView saveAddress(ModelMap model,
                                   @RequestParam Long cityId,
                                   @RequestParam String street,
                                   @RequestParam String region,
                                   @RequestParam Integer postalCode) {
        model.addAttribute("cityId", cityId);
        addressService.createAddress(street, postalCode, region, cityId);
        return new ModelAndView("redirect:/admin/city/edit", model);
    }
}
