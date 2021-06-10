package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CinemaService {

    Optional<Cinema> findWithImagesById(long id);
    Optional<Cinema> findWithHallsById(long id);
    Optional<Cinema> findWithImagesAndHallsById(long id);
    List<Cinema> findAll();

    void addCinema(String name, String description, String rules, MultipartFile mainImage,
                     MultipartFile logoImage, MultipartFile upperBannerImage,
                     MultipartFile[] cinemaImages, Seo seo);

    void updateCinema(Long id, String name, String description, String rules, MultipartFile mainImage,
                      MultipartFile logoImage, MultipartFile upperBannerImage,
                      MultipartFile[] cinemaImages, Seo seo);

    void deleteById(Long id);
}
