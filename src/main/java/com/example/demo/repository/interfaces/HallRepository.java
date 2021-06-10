package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long>{

    @Query(value = "SELECT * FROM hall as h LEFT JOIN hall_images as hi on h.hall_id = hi.hall_id WHERE hall_id = ?1", nativeQuery = true)
    Optional<Hall> findByHallImages(Long id);
    @Query(value = "SELECT * FROM hall as h LEFT JOIN hall_images as hi on h.hall_id = hi.hall_id LEFT JOIN seance s on h.hall_id = s.hall_id WHERE hall_id = ?1", nativeQuery = true)
    Optional<Hall> findByHallImagesAndSeances(Long id);
}
