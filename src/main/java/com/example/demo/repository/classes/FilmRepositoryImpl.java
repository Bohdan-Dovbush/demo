package com.example.demo.repository.classes;

import com.example.demo.entity.film.Film;
import com.example.demo.repository.interfaces.FilmRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FilmRepositoryImpl extends MainRepositoryImpl<Film> implements FilmRepository {

    public FilmRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Film> getClassType() {
        return Film.class;
    }

    @Override
    public Optional<Film> findImagesById(Long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Film> optionalFilm = Optional.ofNullable(session.get(getClassType(),id));
            optionalFilm.ifPresent(film -> Hibernate.initialize(film.getFilmImages()));
            return optionalFilm;
        }
    }
}
