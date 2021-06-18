package com.example.demo.repository;

import com.example.demo.entity.film.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>{

    @Query(value = "SELECT * FROM hall as h LEFT JOIN hall_images as hi on h.hall_id = hi.hall_id WHERE h.hall_id = ?1", nativeQuery = true)
    Optional<Hall> findByHallImages(Long id);
    @Query(value = "SELECT * FROM hall as h LEFT JOIN hall_images as hi on h.hall_id = hi.hall_id LEFT JOIN seance s on h.hall_id = s.hall_id WHERE h.hall_id = ?1", nativeQuery = true)
    Optional<Hall> findByHallImagesAndSeances(Long id);
}
