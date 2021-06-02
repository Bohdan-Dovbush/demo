package com.example.demo.service.interfaces.booking;

import com.example.demo.entity.booking.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.Image;
import com.example.demo.service.interfaces.MainService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CinemaService extends MainService<Cinema> {

    Optional<Cinema> findWithImagesById(long id);

    Optional<Cinema> findWithHallsById(long id);

    Optional<Cinema> findWithImagesAndHallsById(long id);

    List<Cinema> findAll();

    String setMainImageToCinema(long cinemaId, MultipartFile file);

    String setLogoImageToCinema(long cinemaId, MultipartFile file);

    String setUpperBannerImageToCinema(long cinemaId, MultipartFile file);

    void deleteCinemaImage(String cinemaImageName);

    Image addImageToCinema(long cinemaId, MultipartFile file);

    void addCinema(String name, String description, String rules, MultipartFile mainImage,
                     MultipartFile logoImage, MultipartFile upperBannerImage,
                     List<MultipartFile> cinemaImages, Seo seo);

    void updateCinema(Long id, String name, String description, String rules, MultipartFile mainImage,
                      MultipartFile logoImage, MultipartFile upperBannerImage,
                      List<MultipartFile> cinemaImages, List<Long> deletedImages, Seo seo);
}
