package com.example.demo.repository.classes;

import com.example.demo.entity.gallery.FilmImage;
import com.example.demo.repository.interfaces.FilmImageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmImageRepositoryImpl extends MainRepositoryImpl<FilmImage> implements FilmImageRepository {

    public FilmImageRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<FilmImage> getClassType() {
        return FilmImage.class;
    }

    @Override
    public Optional<FilmImage> findByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session
                    .createQuery("SELECT img FROM FilmImage as img WHERE img.image = :name", FilmImage.class)
                    .setParameter("name", name)
                    .uniqueResult());
        }
    }

    @Override
    public void deleteByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM FilmImage as img WHERE img.image = :name")
                    .setParameter("name", name)
                    .executeUpdate();
            tr.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> list) {
        try (Session session = sessionFactory.openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM FilmImage as img WHERE img.id IN :list")
                    .setParameter("list", list)
                    .executeUpdate();
            tr.commit();
        }
    }
}
