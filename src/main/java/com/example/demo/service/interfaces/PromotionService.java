package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.Promotion;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionService {

    List<Promotion> findAll();
    Optional<Promotion> findWithImagesById(Long promotionId);

    void updatePromotion(Long promotionId,
                         String promotionName,
                         String promotionDescription,
                         String promotionVideoLink,
                         Boolean promotionStatus,
                         LocalDate promotionPublishDate,
                         MultipartFile promotionMainImage,
                         MultipartFile[] promotionImages,
                         Seo seo);

    void createPromotion(String promotionName,
                         String promotionDescription,
                         String promotionVideoLink,
                         Boolean promotionStatus,
                         LocalDate promotionPublishDate,
                         MultipartFile promotionMainImage,
                         MultipartFile[] promotionImages,
                         Seo seo);

    void deleteById(Long promotionId);
}
