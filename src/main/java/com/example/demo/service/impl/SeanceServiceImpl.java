package com.example.demo.service.impl;

import com.example.demo.entity.booking.Ticket;
import com.example.demo.entity.film.Seance;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.SeanceRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.interfaces.SeanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final HallRepository hallRepository;
    private final FilmRepository filmRepository;
    private final TicketRepository ticketRepository;

    public SeanceServiceImpl(SeanceRepository seanceRepository, HallRepository hallRepository, FilmRepository filmRepository, TicketRepository ticketRepository) {
        this.seanceRepository = seanceRepository;
        this.hallRepository = hallRepository;
        this.filmRepository = filmRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Seance> findWithFilmById(Long id) {
        return seanceRepository.findWithFilmById(id);
    }

    @Override
    public Optional<Seance> findWithTicketsById(Long id) {
        return seanceRepository.findWithTicketsById(id);
    }

    @Override
    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    @Override
    public List<Seance> findHallAndCinemaByFilmId(Long filmId) {
        return seanceRepository.findHallAndCinemaByFilmId(filmId);
    }

    @Override
    public void createSeance(Long hallId, Long filmId, LocalTime seanceTime) {
        hallRepository.findById(hallId).ifPresent(hall ->
                filmRepository.findById(filmId).ifPresent(film -> {
            Seance seance = new Seance();
            for (int i = 0; i < hall.getCountTicket(); i++) {
                seance.addTicket(new Ticket(100));
            }
            seance.setTime(seanceTime);
            seance.setHall(hall);
            seance.setFilm(film);
            seanceRepository.saveAndFlush(seance);
        }));
    }

    @Override
    public void updateSeance(Long seanceId, Long filmId, LocalTime seanceTime) {
        findWithFilmById(seanceId).ifPresent(seance -> {
            filmRepository.findById(filmId).ifPresent(seance::setFilm);
            seance.setTime(seanceTime);
            seanceRepository.saveAndFlush(seance);
        });
    }

    @Override
    public void buyTicket(Long ticketId, UserEntity user) {
        ticketRepository.findById(ticketId).ifPresent(ticket -> {
            if (!ticket.getIsBooked()){
                ticket.setUserEntity(user);
                ticket.setIsBooked(true);
                ticketRepository.saveAndFlush(ticket);
            }
        });
    }

    @Override
    public void deleteById(Long seanceId) {
        seanceRepository.deleteById(seanceId);
    }
}
