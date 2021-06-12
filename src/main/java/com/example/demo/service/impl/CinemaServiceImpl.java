package com.example.demo.service.impl;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.repository.CinemaRepository;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CinemaServiceImpl extends ImageServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Optional<Cinema> findWithImagesById(long id) {
        return cinemaRepository.findCinemaByCinemaImages(id);
    }

    @Override
    public Optional<Cinema> findWithHallsById(long id) {
        return cinemaRepository.findWithHallsById(id);
    }

    @Override
    public Optional<Cinema> findWithImagesAndHallsById(long id) {
        return cinemaRepository.findWithImagesAndHallsById(id);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public void addCinema(String name,
                          String description,
                          String rules,
                          Boolean isActive,
                          MultipartFile mainImage,
                          MultipartFile logoImage,
                          MultipartFile upperBannerImage,
                          MultipartFile[] cinemaImages,
                          Seo seo) {
        Cinema cinema = new Cinema();
        cinema.setName(name);
        cinema.setDescription(description);
        cinema.setRules(rules);
        cinema.setActive(isActive);
        cinema.setSeo(seo);
        checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImages);
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(Long id,
                             String name,
                             String description,
                             String rules,
                             Boolean isActive,
                             MultipartFile mainImage,
                             MultipartFile logoImage,
                             MultipartFile upperBannerImage,
                             MultipartFile[] cinemaImages,
                             Seo seo) {
        findWithImagesById(id).ifPresent(cinema -> {
            cinema.setName(name);
            cinema.setDescription(description);
            cinema.setRules(rules);
            cinema.setActive(isActive);

            checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImages);

            if (!cinema.getSeo().equals(seo)){
                cinema.setSeo(seo);
            }
            cinemaRepository.saveAndFlush(cinema);
        });
    }

    @Override
    public void deleteById(Long id) {
        Cinema cinema = new Cinema();
        deleteImage(cinema.getMainImage());
        deleteImage(cinema.getLogoImage());
        deleteImage(cinema.getUpperBannerImage());
        deleteImageSet(cinema.getCinemaImages());
        cinemaRepository.deleteById(id);
    }

    void checkCinemaImage(Cinema cinema, MultipartFile mainImage, MultipartFile logoImage, MultipartFile upperBannerImage, MultipartFile[] images) {
        if (!(mainImage == null) && !mainImage.isEmpty()){
            deleteImage(cinema.getMainImage());
            cinema.setMainImage(saveImage(mainImage));
        }
        if (!(logoImage == null) && !logoImage.isEmpty()){
            deleteImage(cinema.getLogoImage());
            cinema.setLogoImage(saveImage(logoImage));
        }
        if (!(upperBannerImage == null) && !upperBannerImage.isEmpty()) {
            deleteImage(cinema.getUpperBannerImage());
            cinema.setUpperBannerImage(saveImage(upperBannerImage));
        }
        if (!(images == null) && !(images.length == 0)) {
            if(!(images[0] == null) && !(images[0].isEmpty())) {
                deleteImageSet(cinema.getCinemaImages());
                cinema.setCinemaImages(saveImageArray(images));
            }
        }
    }
}
