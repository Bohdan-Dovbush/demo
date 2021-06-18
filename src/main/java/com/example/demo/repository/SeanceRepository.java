package com.example.demo.repository;

import com.example.demo.entity.film.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query(value = "SELECT * FROM seance as s left join film f on f.film_id = s.film_id WHERE s.seance_id = ?1", nativeQuery = true)
    Optional<Seance> findWithFilmById(Long id);
    @Query(value = "SELECT * FROM seance as s left join ticket t on s.seance_id = t.seance_id WHERE s.film_id = ?1", nativeQuery = true)
    Optional<Seance> findWithTicketsById(Long id);
    @Query(value = "SELECT * FROM seance as s left join hall h on h.hall_id = s.hall_id left join cinema c on c.cinema_id = h.cinema_id WHERE s.film_id = ?1", nativeQuery = true)
    List<Seance> findHallAndCinemaByFilmId(Long filmId);
}
