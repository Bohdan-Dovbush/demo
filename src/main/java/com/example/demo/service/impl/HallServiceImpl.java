package com.example.demo.service.impl;

import com.example.demo.entity.film.Hall;
import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.HallImage;
import com.example.demo.repository.interfaces.CinemaRepository;
import com.example.demo.repository.interfaces.HallRepository;
import com.example.demo.repository.interfaces.HallImageRepository;
import com.example.demo.service.interfaces.HallService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl extends MainServiceImpl<Hall> implements HallService {

    private final HallRepository hallRepository;
    private final HallImageRepository imageRepository;
    private final CinemaRepository cinemaRepository;

    public HallServiceImpl(HallRepository hallRepository, HallImageRepository imageRepository, CinemaRepository cinemaRepository) {
        super(hallRepository);
        this.hallRepository = hallRepository;
        this.imageRepository = imageRepository;
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
    public String addSchemaImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + "." + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                hall.setSchemaImage(fileName);
                hallRepository.update(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String addBannerImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + "." + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                hall.setBannerImage(fileName);
                hallRepository.update(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public HallImage addHallImage(Long id, MultipartFile multipartFile) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent() && !multipartFile.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + "." + multipartFile.getOriginalFilename();
            if (saveFile(fileName,multipartFile)){
                HallImage image = new HallImage(fileName);
                hall.addHallImage(image);
                hallRepository.update(hall);
                return image;
            }
        }
        return null;
    }

    @Override
    public void createHall(String name, String description, Long cinemaId, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, Seo seo) {
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
    public void updateHall(Long id, String name, String description, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, List<Long> deletedImages, Seo seo) {
        hallRepository.findByHallImages(id).ifPresent(hall -> {
            hall.setName(name);
            hall.setDescription(description);
            if (!hall.getSeo().equals(seo)) {
                hall.setSeo(seo);
            }
            checkHallImage(hall, hallSchemaImage, hallBannerImage, hallImages);
            hallRepository.update(hall);
            if (deletedImages != null){
                imageRepository.deleteByListOfId(deletedImages);
            }
        });
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
        if (!hallImages.isEmpty()){
            hallImages.forEach(image -> hall.addHallImage(new HallImage(saveImageAndGetName(image))));
        }
    }
}
