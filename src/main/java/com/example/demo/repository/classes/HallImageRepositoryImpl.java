package com.example.demo.repository.classes;

import com.example.demo.entity.gallery.HallImage;
import com.example.demo.repository.interfaces.HallImageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HallImageRepositoryImpl extends MainRepositoryImpl<HallImage> implements HallImageRepository {

    public HallImageRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<HallImage> getClassType() {
        return HallImage.class;
    }

    @Override
    public void deleteImageByName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM HallImage img WHERE img.image = :name")
                    .setParameter("name",name)
                    .executeUpdate();
            ts.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> deletedImages) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM HallImage  img WHERE img.id IN :list")
                    .setParameter("list",deletedImages)
                    .executeUpdate();
            ts.commit();
        }
    }
}
