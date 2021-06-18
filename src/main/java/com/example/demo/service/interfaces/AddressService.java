package com.example.demo.service.interfaces;

import com.example.demo.entity.address.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();
    Optional<Address> findById(Long id);
    void createAddress(String street,
                       Integer postalCode,
                       String region,
                       Long cityId);
    void updateAddress(Long addressId,
                       String street,
                       Integer postalCode,
                       String region);
    void deleteById(Long id);
}
