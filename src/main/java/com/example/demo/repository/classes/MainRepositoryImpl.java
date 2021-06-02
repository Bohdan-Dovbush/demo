package com.example.demo.repository.classes;

import com.example.demo.repository.interfaces.MainRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class MainRepositoryImpl<T> implements MainRepository<T> {

    protected final SessionFactory sessionFactory;
    public abstract Class<T> getClassType();

    @Autowired
    public MainRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public Optional<T> findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(getClassType(), id));
        }
    }

    @Override
    public void delete(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.delete(object);
            tr.commit();
        }
    }

    @Override
    public void deleteById(long id) {
        try (Session session = sessionFactory.openSession()) {
            T object = session.get(getClassType(), id);
            if (object != null) {
                Transaction tr = session.beginTransaction();
                session.delete(object);
                tr.commit();
            }
        }
    }

    @Override
    public void update(T object) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.update(object);
            tr.commit();
        }
    }

    @Override
    public void save(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.save(object);
            tr.commit();
        }
    }
}
