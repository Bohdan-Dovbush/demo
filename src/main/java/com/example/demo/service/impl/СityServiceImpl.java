package com.example.demo.service.impl;

import com.example.demo.entity.address.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.interfaces.СityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class СityServiceImpl implements СityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public СityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<City> findCityAndAddressById(Long id) {
        return cityRepository.findCityAndAddressById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public void createCity(String name, Long countryId) {
        countryRepository.findCountryAndCityById(countryId).ifPresent(country -> {
            City city = new City();
            city.setName(name);
            city.setCountry(country);
            cityRepository.saveAndFlush(city);
        });
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
