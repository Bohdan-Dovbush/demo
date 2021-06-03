package com.example.demo.service.impl;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.CinemaImage;
import com.example.demo.repository.interfaces.CinemaImageRepository;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl extends MainServiceImpl<Cinema> implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaImageRepository imageRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaImageRepository imageRepository) {
        super(cinemaRepository);
        this.cinemaRepository = cinemaRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Cinema> findWithImagesById(long id) {
        return cinemaRepository.findWithImagesById(id);
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
    public String setMainImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setMainImage(fileName);
                update(cinema);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String setLogoImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setLogoImage(fileName);
                update(cinema);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String setUpperBannerImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setUpperBannerImage(fileName);
                update(cinema);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public void deleteCinemaImage(String cinemaImageName) {
        imageRepository.deleteByImageName(cinemaImageName);
    }

    @Override
    public CinemaImage addImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaRepository.findWithImagesById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                CinemaImage image = new CinemaImage(fileName);
                cinema.addCinemaImage(image);
                update(cinema);
                return image;
            }
        }
        return null;
    }

    @Override
    public void addCinema(String name, String description, String rules, MultipartFile mainImage, MultipartFile logoImage, MultipartFile upperBannerImage, List<MultipartFile> cinemaImages, Seo seo) {
        Cinema cinema = new Cinema();
        cinema.setName(name);
        cinema.setDescription(description);
        cinema.setRules(rules);
        cinema.setSeo(seo);
        checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImages);
        cinemaRepository.save(cinema);
    }

    @Override
    public void updateCinema(Long id, String name, String description, String rules, MultipartFile mainImage, MultipartFile logoImage, MultipartFile upperBannerImage, List<MultipartFile> cinemaImages, List<Long> deletedImages, Seo seo) {
        findWithImagesById(id).ifPresent(cinema -> {
            cinema.setName(name);
            cinema.setDescription(description);
            cinema.setRules(rules);

            checkCinemaImage(cinema, mainImage, logoImage, upperBannerImage, cinemaImages);

            if (!cinema.getSeo().equals(seo)){
                cinema.setSeo(seo);
            }
            cinemaRepository.update(cinema);

            if (deletedImages != null){
                imageRepository.deleteByListOfId(deletedImages);
            }
        });
    }

    void checkCinemaImage(Cinema cinema, MultipartFile mainImage, MultipartFile cinemaLogoImage, MultipartFile cinemaUpperBannerImage, List<MultipartFile> cinemaImages) {
        if (!mainImage.isEmpty()){
            cinema.setMainImage(saveImageAndGetName(mainImage));
        }
        if (!cinemaLogoImage.isEmpty()){
            cinema.setLogoImage(saveImageAndGetName(cinemaLogoImage));
        }
        if (!cinemaUpperBannerImage.isEmpty()){
            cinema.setUpperBannerImage(saveImageAndGetName(cinemaUpperBannerImage));
        }
        if (!cinemaImages.isEmpty()){
            cinemaImages.forEach(image -> cinema.addCinemaImage(new CinemaImage(saveImageAndGetName(image))));
        }
    }
}
