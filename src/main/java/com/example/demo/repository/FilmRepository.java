package com.example.demo.repository;

import com.example.demo.entity.film.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "SELECT * FROM Film as f LEFT JOIN film_images fi on f.film_id = fi.film_id WHERE f.film_id = ?1", nativeQuery = true)
    Optional<Film> findImagesById(Long id);
    @Query(value = "SELECT COUNT(*) FROM film", nativeQuery = true)
    Integer findTotalCountFilm();
    @Query(value = "SELECT COUNT(*) FROM film WHERE DATE(now()) BETWEEN date_release and date_finish", nativeQuery = true)
    Integer findCurrentCountFilm();
    @Query(value = "SELECT COUNT(*) FROM film WHERE date_release > DATE(now())", nativeQuery = true)
    Integer findFutureCountFilm();
    @Query(value = "SELECT * FROM film WHERE DATE(now()) BETWEEN date_release and date_finish", nativeQuery = true)
    List<Film> findCurrentFilm();
    @Query(value = "SELECT * FROM film WHERE date_release > DATE(now())", nativeQuery = true)
    List<Film> findFutureFilm();
}
