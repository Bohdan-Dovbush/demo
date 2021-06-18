package com.example.demo.service.interfaces;

import com.example.demo.entity.address.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findAllContactById(Long id);
    List<Country> findAll();
    void createCountry(String name);
    void deleteById(Long id);
}
