package com.example.demo.service.impl;

import com.example.demo.service.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class ImageServiceImpl implements ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String saveImage(MultipartFile image) {

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

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
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        File imageFile = new File(uploadPath + "/" + imageName);
        if(!imageFile.exists()) {
            imageFile.delete();
        }
    }

    @Override
    public void deleteImageSet(Set<String> gallery) {
        if(!(gallery == null) && !gallery.isEmpty()) {
            for(String image : gallery)
                deleteImage(image);
        }
    }

    protected String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
