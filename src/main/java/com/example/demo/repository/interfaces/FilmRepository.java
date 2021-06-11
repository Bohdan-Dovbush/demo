package com.example.demo.repository.interfaces;

import com.example.demo.entity.film.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "SELECT * FROM Film as f LEFT JOIN film_images fi on f.film_id = fi.film_id WHERE f.film_id = ?1", nativeQuery = true)
    Optional<Film> findImagesById(Long id);
}
