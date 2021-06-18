package com.example.demo.repository;

import com.example.demo.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM city as c left join address a on c.city_id = a.city_id WHERE c.city_id = ?1", nativeQuery = true)
    Optional<City> findCityAndAddressById(Long id);
    @Query(value = "SELECT DISTINCT * FROM city as c left join address a on c.city_id = a.city_id", nativeQuery = true)
    List<City> findAllCityAndAddress();
}
