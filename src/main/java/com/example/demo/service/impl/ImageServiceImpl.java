package com.example.demo.service.impl;

import com.example.demo.entity.gallery.Image;
import com.example.demo.repository.interfaces.Repo;
import com.example.demo.service.interfaces.user.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class ImageServiceImpl implements ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final Repo<Image, Long> repo;

    public ImageServiceImpl(Repo<Image, Long> repo) {
        this.repo = repo;
    }

    @Override
    public String saveImage(MultipartFile image) {

        String resultFilename = getRandomUUID() + "." + image.getOriginalFilename();
        try {
            image.transferTo(new File(uploadPath + "/" + resultFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFilename;
    }

    @Override
    public Set<String> saveImageArray(MultipartFile[] imageArray) {
        Set<String> imageSet = new HashSet<>();
        for (MultipartFile image : imageArray)
            imageSet.add(saveImage(image));
        return imageSet;
    }

    @Override
    public void deleteImage(String imageName) {
        File imageFile = new File(uploadPath + "/" + imageName);
        imageFile.delete();
    }

    @Override
    public void deleteImageSet(Set<String> gallery) {
        for(String image : gallery)
            deleteImage(image);
    }

    @Override
    public void save(Image image) {
        repo.save(image);
    }

    @Override
    public Image getById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Page<Image> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    protected String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
