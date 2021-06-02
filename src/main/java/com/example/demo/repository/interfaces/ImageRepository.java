package com.example.demo.repository.interfaces;

import com.example.demo.entity.gallery.Image;

import java.util.List;

public interface ImageRepository extends MainRepository<Image> {

    void deleteByImageName(String name);
    void deleteByListOfId(List<Long> deletedImages);
}
