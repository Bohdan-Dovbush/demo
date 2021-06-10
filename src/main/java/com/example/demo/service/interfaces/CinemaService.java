package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.service.interfaces.user.ImageService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CinemaService extends ImageService {

    Optional<Cinema> findWithImagesById(long id);
    Optional<Cinema> findWithHallsById(long id);
    Optional<Cinema> findWithImagesAndHallsById(long id);
    List<Cinema> findAll();

    void addCinema(String name, String description, String rules, MultipartFile mainImage,
                     MultipartFile logoImage, MultipartFile upperBannerImage, MultipartFile[] cinemaImage, Seo seo);

    void updateCinema(Long id, String name, String description, String rules, Boolean isActive, MultipartFile mainImage,
                      MultipartFile logoImage, MultipartFile upperBannerImage, MultipartFile[] cinemaImage, Seo seo);

    void deleteById(Long id);

    Cinema save(Cinema cinema);
}
