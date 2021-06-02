package com.example.demo.service.impl.booking;

import com.example.demo.entity.booking.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.Image;
import com.example.demo.repository.interfaces.ImageRepository;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.repository.interfaces.HallRepository;
import com.example.demo.service.interfaces.booking.HallService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HallServiceImpl implements HallService {

    @Value("${upload.path}")
    private String imagePath;
    private final HallRepository hallRepository;
    private final ImageRepository imageRepository;
    private final CinemaRepository cinemaRepository;

    public HallServiceImpl(HallRepository hallRepository, ImageRepository imageRepository, CinemaRepository cinemaRepository) {
        this.hallRepository = hallRepository;
        this.imageRepository = imageRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Optional<Hall> findByHallImages(Long id) {
        return hallRepository.findByHallImages(id);
    }

    @Override
    public Optional<Hall> findByHallImagesAndSeances(Long image, Long seance) {
        return hallRepository.findByHallImagesAndSeances(image, seance);
    }

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public String setSchemaImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                hall.setSchemaImage(fileName);
                hallRepository.saveAndFlush(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String setBannerImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                hall.setBannerImage(fileName);
                hallRepository.saveAndFlush(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public Image addHallImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                Image image = new Image(fileName);
                hall.addHallImage(image);
                hallRepository.saveAndFlush(hall);
                return image;
            }
        }
        return null;
    }

    @Override
    public void createHall(String name, String description, Long cinemaId, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, List<Long> deletedImages, Seo seo) {
        cinemaRepository.findWithHallsById(cinemaId).ifPresent(cinema -> {
            Hall hall = new Hall();
            hall.setName(name);
            hall.setDescription(description);
            hall.setCinema(cinema);
            hall.setSeo(seo);
            checkHallImage(hall, hallSchemaImage, hallBannerImage, hallImages);
            hallRepository.saveAndFlush(hall);
        });
    }

    @Override
    public void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, List<Long> deletedImages, Seo seo) {

    }

    @Override
    public void deleteHallImage(Long id) {
        imageRepository.deleteById(id);
    }

    void checkHallImage(Hall hall, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages) {
        if (!hallSchemaImage.isEmpty()){
            hall.setSchemaImage(saveImageAndGetName(hallSchemaImage));
        }
        if (!hallBannerImage.isEmpty()){
            hall.setBannerImage(saveImageAndGetName(hallBannerImage));
        }
        if (hallImages != null){
            hallImages.forEach(image -> hall.addHallImage(new Image(saveImageAndGetName(image))));
        }
    }

    public String saveImageAndGetName(MultipartFile file) {
        if (!file.isEmpty()){
            String filename = getRandomUUID() + file.getOriginalFilename();
            saveFile(filename,file);
            return filename;
        }
        return null;
    }

    public boolean saveFile(String fileName, MultipartFile file) {
        try {
            file.transferTo(new File(imagePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
