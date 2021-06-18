package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Seance;
import com.example.demo.entity.user.UserEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SeanceService {

    Optional<Seance> findWithFilmById(Long id);
    Optional<Seance> findWithTicketsById(Long id);
    List<Seance> findAll();
    List<Seance> findHallAndCinemaByFilmId(Long filmId);
    void createSeance(Long hallId, Long filmId, LocalTime seanceTime);
    void updateSeance(Long seanceId, Long filmId, LocalTime seanceTime);
    void buyTicket(Long ticketId, UserEntity user);
    void deleteById(Long seanceId);
}
