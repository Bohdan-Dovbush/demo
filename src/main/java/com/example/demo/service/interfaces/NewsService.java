package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.News;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    List<News> findAll();
    Optional<News> findWithImagesById(Long newsId);

    void updateNews(Long newsId,
                    String newsName,
                    String newsDescription,
                    String newsVideoLink,
                    Boolean newsStatus,
                    LocalDate newsPublishDate,
                    MultipartFile newsMainImage,
                    MultipartFile[] newsImages,
                    Seo seo);

    void createNews(String newsName,
                    String newsDescription,
                    String newsVideoLink,
                    Boolean newsStatus,
                    LocalDate newsPublishDate,
                    MultipartFile newsMainImage,
                    MultipartFile[] newsImages,
                    Seo seo);

    void deleteById(Long newsId);
}
