package com.example.demo.service.impl;

import com.example.demo.entity.film.Cinema;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.CinemaImage;
import com.example.demo.repository.interfaces.CinemaImageRepository;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.service.interfaces.CinemaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {

    @Value("${upload.path}")
    private String uploadPath;

    private final CinemaRepository cinemaRepository;
    private final CinemaImageRepository imageRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaImageRepository imageRepository) {
        this.cinemaRepository = cinemaRepository;
        this.imageRepository = imageRepository;
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
    public String setMainImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setMainImage(fileName);
                cinemaRepository.save(cinema);
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
                cinemaRepository.save(cinema);
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
                cinemaRepository.save(cinema);
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
        Optional<Cinema> optionalCinema = cinemaRepository.findCinemaByCinemaImages(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                CinemaImage image = new CinemaImage(fileName);
                cinema.addCinemaImage(image);
                cinemaRepository.save(cinema);
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
            cinemaRepository.save(cinema);

            if (deletedImages != null){
                imageRepository.deleteByListOfId(deletedImages);
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public Cinema save(Cinema cinema) {
        cinemaRepository.save(cinema);
        return cinema;
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

    public String saveImageAndGetName(MultipartFile file) {
        if (!file.isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = getRandomUUID() + "." + file.getOriginalFilename();
            saveFile(fileName,file);
            return fileName;
        }
        return null;
    }

    protected boolean saveFile(String fileName, MultipartFile file) {
        try {
            file.transferTo(new File(uploadPath + "/"+  fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
