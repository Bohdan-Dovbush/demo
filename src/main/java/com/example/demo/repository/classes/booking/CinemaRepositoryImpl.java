package com.example.demo.repository.classes.booking;

import com.example.demo.entity.booking.Cinema;
import com.example.demo.repository.classes.MainRepositoryImpl;
import com.example.demo.repository.interfaces.CinemaRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CinemaRepositoryImpl extends MainRepositoryImpl<Cinema> implements CinemaRepository {

    public CinemaRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Cinema> getClassType() {
        return Cinema.class;
    }

    @Override
    public Optional<Cinema> findWithImagesById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema -> Hibernate.initialize(cinema.getCinemaImages()));
            return optional;
        }

    }

    @Override
    public Optional<Cinema> findWithHallsById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema -> Hibernate.initialize(cinema.getHalls()));
            return optional;
        }
    }

    @Override
    public Optional<Cinema> findWithImagesAndHallsById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema ->{
                Hibernate.initialize(cinema.getCinemaImages());
                Hibernate.initialize(cinema.getHalls());
            });
            return optional;
        }
    }

    @Override
    public List<Cinema> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT c FROM Cinema as c",Cinema.class).getResultList();
        }
    }
}
