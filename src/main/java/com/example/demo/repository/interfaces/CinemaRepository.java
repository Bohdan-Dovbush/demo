package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query(value = "SELECT * FROM cinema as c LEFT JOIN cinema_image ci on c.cinema_id = ci.cinema_id where c.cinema_id = ?1", nativeQuery = true)
    Optional<Cinema> findCinemaByCinemaImages(Long id);
    @Query(value = "SELECT * FROM cinema as c LEFT JOIN hall as h on c.cinema_id = h.cinema_id where c.cinema_id = ?1", nativeQuery = true)
    Optional<Cinema> findWithHallsById(Long id);
    @Query(value = "SELECT * FROM cinema as c LEFT JOIN cinema_image ci on c.cinema_id = ci.cinema_id LEFT JOIN hall h on c.cinema_id = h.cinema_id where c.cinema_id = ?1", nativeQuery = true)
    Optional<Cinema> findWithImagesAndHallsById(Long id);
}
