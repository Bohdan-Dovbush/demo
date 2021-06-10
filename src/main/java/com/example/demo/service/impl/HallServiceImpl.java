package com.example.demo.service.impl;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.repository.interfaces.HallRepository;
import com.example.demo.service.interfaces.HallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HallServiceImpl extends ImageServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    public HallServiceImpl(HallRepository hallRepository, CinemaRepository cinemaRepository) {
        this.hallRepository = hallRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Optional<Hall> findByHallImages(Long id) {
        return hallRepository.findByHallImages(id);
    }

    @Override
    public Optional<Hall> findByHallImagesAndSeances(Long id) {
        return hallRepository.findByHallImagesAndSeances(id);
    }

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public void createHall(String name, String description, Long cinemaId, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, MultipartFile[] hallImages, Seo seo) {
        cinemaRepository.findWithHallsById(cinemaId).ifPresent(cinema -> {
            Hall hall = new Hall();
            hall.setName(name);
            hall.setDescription(description);
            hall.setCinema(cinema);
            hall.setSeo(seo);
            checkHallImage(hall, hallSchemaImage, hallBannerImage, hallImages);
            hallRepository.save(hall);
        });
    }

    @Override
    public void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, MultipartFile[] hallImages, Seo seo) {
        hallRepository.findByHallImages(id).ifPresent(hall -> {
            hall.setName(name);
            hall.setDescription(description);
            if (!hall.getSeo().equals(seo)) {
                hall.setSeo(seo);
            }
            checkHallImage(hall, hallSchemaImage, hallBannerImage, hallImages);
            hallRepository.saveAndFlush(hall);
        });
    }

    @Override
    public void deleteById(Long id) {
        Hall hall = new Hall();
        deleteImage(hall.getSchemaImage());
        deleteImage(hall.getBannerImage());
        deleteImageSet(hall.getHallImages());
        hallRepository.deleteById(id);
    }

    void checkHallImage(Hall hall, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, MultipartFile[] hallImages) {
        if (!(hallSchemaImage == null) && !hallSchemaImage.isEmpty()) {
            deleteImage(hall.getSchemaImage());
            hall.setSchemaImage(saveImage(hallSchemaImage));
        }
        if (!(hallBannerImage == null) && !hallBannerImage.isEmpty()) {
            deleteImage(hall.getBannerImage());
            hall.setBannerImage(saveImage(hallBannerImage));
        }
        if (!(hallImages == null) && !(hallImages.length == 0)) {
            if(!(hallImages[0] == null) && !(hallImages[0].isEmpty())) {
                deleteImageSet(hall.getHallImages());
                hall.setHallImages(saveImageArray(hallImages));
            }
        }
    }
}
