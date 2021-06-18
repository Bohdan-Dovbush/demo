package com.example.demo.service.interfaces;

import com.example.demo.entity.address.City;

import java.util.List;
import java.util.Optional;

public interface СityService {

    Optional<City> findCityAndAddressById(Long id);
    List<City> findAll();
    void createCity(String name, Long countryId);
    void deleteById(Long id);
}
