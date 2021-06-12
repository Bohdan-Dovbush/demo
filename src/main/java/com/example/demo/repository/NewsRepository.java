package com.example.demo.repository;

import com.example.demo.entity.user.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * FROM news as n LEFT JOIN news_images as ni on n.news_id = ni.news_id where n.news_id = ?1", nativeQuery = true)
    Optional<News> findWithImagesById(Long newsId);
}
