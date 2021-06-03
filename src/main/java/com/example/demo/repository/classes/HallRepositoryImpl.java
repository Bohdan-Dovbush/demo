package com.example.demo.repository.classes;

import com.example.demo.entity.film.Hall;
import com.example.demo.repository.interfaces.HallRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HallRepositoryImpl extends MainRepositoryImpl<Hall> implements HallRepository {

    public HallRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Hall> getClassType() {
        return Hall.class;
    }

    @Override
    public Optional<Hall> findByHallImages(Long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Hall> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(hall -> Hibernate.initialize(hall.getHallImages()));
            return optional;
        }
    }

    @Override
    public Optional<Hall> findByHallImagesAndSeances(Long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Hall> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(hall ->{
                Hibernate.initialize(hall.getHallImages());
                Hibernate.initialize(hall.getSeances());
            } );
            return optional;
        }
    }
}
