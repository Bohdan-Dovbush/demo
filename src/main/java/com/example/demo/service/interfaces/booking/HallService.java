package com.example.demo.service.interfaces.booking;

import com.example.demo.entity.booking.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HallService {

    Optional<Hall> findByHallImages(Long id);
    Optional<Hall> findByHallImagesAndSeances(Long image, Long seance);
    List<Hall> findAll();

    String setSchemaImage(Long  id, MultipartFile multipartFile);
    String setBannerImage(Long  id, MultipartFile multipartFile);
    Image addHallImage(Long  id, MultipartFile multipartFile);

    void createHall(String name, String description, Long cinemaId, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, List<Long> deletedImages, Seo seo);
    void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, List<Long> deletedImages, Seo seo);
    void deleteHallImage(Long id);
}
