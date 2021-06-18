package com.example.demo.service.impl;

import com.example.demo.entity.address.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.interfaces.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findAllContactById(Long id) {
        return countryRepository.findAllContactById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public void createCountry(String name) {
        Country country = new Country();
        country.setName(name);
        countryRepository.saveAndFlush(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
