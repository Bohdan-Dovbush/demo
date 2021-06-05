package com.example.demo.service.interfaces;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.HallImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HallService extends MainService<Hall> {

    Optional<Hall> findByHallImages(Long id);
    Optional<Hall> findByHallImagesAndSeances(Long id);
    List<Hall> findAll();
    String addSchemaImage(Long  id, MultipartFile multipartFile);
    String addBannerImage(Long  id, MultipartFile multipartFile);
    HallImage addHallImage(Long  id, MultipartFile multipartFile);

    void createHall(String name, String description, Long cinemaId,
                    MultipartFile hallSchemaImage, MultipartFile hallBannerImage,
                    List<MultipartFile> hallImages, Seo seo);
    void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage,
                    MultipartFile hallBannerImage, List<MultipartFile> hallImages,
                    List<Long> deletedImages, Seo seo);
    void deleteHallImage(Long id);
}
