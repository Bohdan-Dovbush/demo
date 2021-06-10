package com.example.demo.service.interfaces.user;

import com.example.demo.entity.gallery.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ImageService {

    String saveImage(MultipartFile image);

    Set<String> saveImageArray(MultipartFile[] imageArray);

    void deleteImage(String imageName);

    void deleteImageSet(Set<String> gallery);

    void save(Image image);

    Image getById(Long id);

    Page<Image> getAll(Pageable pageable);
}
