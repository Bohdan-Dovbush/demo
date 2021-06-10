package com.example.demo.service.impl;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.repository.interfaces.Repo;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CinemaServiceImpl extends ImageServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        super((Repo) cinemaRepository);
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
    public void addCinema(String name, String description, String rules, MultipartFile mainImage,
                          MultipartFile logoImage, MultipartFile upperBannerImage, MultipartFile[] cinemaImage, Seo seo) {
        Cinema cinema = new Cinema();
        cinema.setName(name);
        cinema.setDescription(description);
        cinema.setRules(rules);
        cinema.setSeo(seo);
        checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImage );
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(Long id, String name, String description, String rules, Boolean isActive,
                             MultipartFile mainImage, MultipartFile logoImage,
                             MultipartFile upperBannerImage, MultipartFile[] cinemaImage, Seo seo) {
        findWithImagesById(id).ifPresent(cinema -> {
            cinema.setName(name);
            cinema.setDescription(description);
            cinema.setRules(rules);
            cinema.setActive(isActive);

            checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImage);

            if (!cinema.getSeo().equals(seo)){
                cinema.setSeo(seo);
            }
            cinemaRepository.save(cinema);
        });
    }

    @Override
    public void deleteById(Long id) {
        findWithImagesById(id).ifPresent(cinema -> {
        deleteImage(cinema.getLogoImage());
        deleteImage(cinema.getMainImage());
        deleteImage(cinema.getUpperBannerImage());
        deleteImageSet(cinema.getImagesGallery());
        cinemaRepository.deleteById(id);
        });
    }

    @Override
    public Cinema save(Cinema cinema) {
        cinemaRepository.save(cinema);
        return cinema;
    }

    void checkCinemaImage(Cinema cinema, MultipartFile mainImage, MultipartFile cinemaLogoImage, MultipartFile cinemaUpperBannerImage, MultipartFile[] cinemaImage) {
        if (!mainImage.isEmpty()){
            deleteImage(cinema.getMainImage());
            cinema.setMainImage(saveImage(mainImage));
        }
        if (!cinemaLogoImage.isEmpty()){
            deleteImage(cinema.getLogoImage());
            cinema.setLogoImage(saveImage(cinemaLogoImage));
        }
        if (!cinemaUpperBannerImage.isEmpty()){
            deleteImage(cinema.getUpperBannerImage());
            cinema.setUpperBannerImage(saveImage(cinemaUpperBannerImage));
        }

        if(!(cinemaImage == null) && !(cinemaImage.length == 0)) {
            if (!cinemaImage[0].isEmpty() && !(cinemaImage[0] == null)) {
                deleteImageSet(cinema.getImagesGallery());
                cinema.setImagesGallery(saveImageArray(cinemaImage));
            }
        }
    }

    protected String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
