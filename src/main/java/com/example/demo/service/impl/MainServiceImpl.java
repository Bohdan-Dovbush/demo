package com.example.demo.service.impl;

import com.example.demo.repository.interfaces.MainRepository;
import com.example.demo.service.interfaces.MainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public abstract class MainServiceImpl<T> implements MainService<T> {

    @Value("${upload.path}")
    private String uploadPath;
    private final MainRepository<T> mainRepository;

    public MainServiceImpl(MainRepository<T> mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public Optional<T> findById(Long id) {
        return mainRepository.findById(id);
    }

    @Override
    public void delete(T object) {
        mainRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        mainRepository.deleteById(id);
    }

    @Override
    public void update(T object) {
        mainRepository.update(object);
    }

    @Override
    public void save(T object) {
        mainRepository.save(object);
    }

    @Override
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
