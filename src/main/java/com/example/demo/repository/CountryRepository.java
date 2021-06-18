package com.example.demo.repository;

import com.example.demo.entity.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT * FROM country as c left join city c2 on c.country_id = c2.country_id left join address a on c2.city_id = a.city_id WHERE c.country_id = ?1", nativeQuery = true)
    Optional<Country> findAllContactById(Long id);
    @Query(value = "SELECT * FROM country as c left join city c2 on c.country_id = c2.country_id WHERE c.country_id = ?1", nativeQuery = true)
    Optional<Country> findCountryAndCityById(Long id);
}
