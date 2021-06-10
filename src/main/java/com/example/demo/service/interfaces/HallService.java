package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HallService {

    Optional<Hall> findByHallImages(Long id);
    Optional<Hall> findByHallImagesAndSeances(Long id);
    List<Hall> findAll();

    void createHall(String name, String description, Long cinemaId,
                    MultipartFile hallSchemaImage, MultipartFile hallBannerImage,
                    MultipartFile[] hallImages, Seo seo);
    void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage,
                    MultipartFile hallBannerImage, MultipartFile[] hallImages, Seo seo);
    void deleteById(Long id);
}
