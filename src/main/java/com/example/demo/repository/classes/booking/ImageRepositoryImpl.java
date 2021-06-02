package com.example.demo.repository.classes.booking;

import com.example.demo.entity.gallery.Image;
import com.example.demo.repository.classes.MainRepositoryImpl;
import com.example.demo.repository.interfaces.ImageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl extends MainRepositoryImpl<Image> implements ImageRepository {

    public ImageRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Image> getClassType() {
        return null;
    }

    @Override
    public void deleteByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM Image img WHERE img.image = :name")
                    .setParameter("name",name)
                    .executeUpdate();
            ts.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> deletedImages) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM Image  img WHERE img.id IN :list")
                    .setParameter("list",deletedImages)
                    .executeUpdate();
            ts.commit();
        }
    }
}
