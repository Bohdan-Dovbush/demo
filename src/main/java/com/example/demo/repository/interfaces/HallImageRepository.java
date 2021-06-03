package com.example.demo.repository.interfaces;

import com.example.demo.entity.gallery.HallImage;

import java.util.List;

public interface HallImageRepository extends MainRepository<HallImage> {

    void deleteImageByName(String name);
    void deleteByListOfId(List<Long> deletedImages);
}
