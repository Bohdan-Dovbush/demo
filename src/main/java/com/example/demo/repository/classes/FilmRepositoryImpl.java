package com.example.demo.repository.classes;

import com.example.demo.entity.film.Film;
import com.example.demo.repository.interfaces.FilmRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
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
            Optional<Film> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie -> Hibernate.initialize(movie.getFilmImages()));
            return optionalMovie;
        }
    }

    @Override
    public Optional<Film> findTypesById(Long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Film> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie -> Hibernate.initialize(movie.getType()));
            return optionalMovie;
        }
    }

    @Override
    public Optional<Film> findImagesAndTypesById(Long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Film> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie ->{
                Hibernate.initialize(movie.getFilmImages());
                Hibernate.initialize(movie.getType());
            });
            return optionalMovie;
        }
    }

    @Override
    public List<Film> findAllWithTypes() {
        try (Session session = sessionFactory.openSession()){
            List<Film> movies = session
                    .createQuery("SELECT f FROM Film as f", getClassType())
                    .getResultList();
            movies.forEach(movie -> Hibernate.initialize(movie.getType()));
            return movies;
        }
    }
}
