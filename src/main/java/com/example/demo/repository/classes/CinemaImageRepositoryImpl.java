//package com.example.demo.repository.classes;
//
//import com.example.demo.entity.gallery.CinemaImage;
//import com.example.demo.repository.classes.MainRepositoryImpl;
//import com.example.demo.repository.interfaces.CinemaImageRepository;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CinemaImageRepositoryImpl extends MainRepositoryImpl<CinemaImage> implements CinemaImageRepository {
//
//    public CinemaImageRepositoryImpl(SessionFactory sessionFactory) {
//        super(sessionFactory);
//    }
//
//    @Override
//    public Class<CinemaImage> getClassType() {
//        return CinemaImage.class;
//    }
//
//    @Override
//    public void deleteByImageName(String name) {
//        try (Session session = sessionFactory.openSession()){
//            Transaction ts = session.beginTransaction();
//            session.createQuery("DELETE FROM CinemaImage img WHERE img.image = :name")
//                    .setParameter("name",name)
//                    .executeUpdate();
//            ts.commit();
//        }
//    }
//
//    @Override
//    public void deleteByListOfId(List<Long> deletedImages) {
//        try (Session session = sessionFactory.openSession()){
//            Transaction ts = session.beginTransaction();
//            session.createQuery("DELETE FROM CinemaImage  img WHERE img.id IN :list")
//                    .setParameter("list",deletedImages)
//                    .executeUpdate();
//            ts.commit();
//        }
//    }
//}
