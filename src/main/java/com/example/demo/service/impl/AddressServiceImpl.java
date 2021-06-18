package com.example.demo.service.impl;

import com.example.demo.entity.address.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.interfaces.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void createAddress(String street, Integer postalCode, String region, Long cityId) {
        cityRepository.findCityAndAddressById(cityId).ifPresent(city -> {
            Address address = new Address();
            address.setStreetAddress(street);
            address.setPostalCode(postalCode);
            address.setRegion(region);
            address.setCity(city);
            addressRepository.saveAndFlush(address);
        });
    }

    @Override
    public void updateAddress(Long addressId, String street, Integer postalCode, String region) {
        findById(addressId).ifPresent(address -> {
            address.setStreetAddress(street);
            address.setRegion(region);
            address.setPostalCode(postalCode);
            addressRepository.saveAndFlush(address);
        });
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
