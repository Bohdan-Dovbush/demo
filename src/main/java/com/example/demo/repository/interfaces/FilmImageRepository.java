package com.example.demo.repository.interfaces;

import com.example.demo.entity.gallery.FilmImage;

import java.util.List;
import java.util.Optional;

public interface FilmImageRepository extends MainRepository<FilmImage>{

    Optional<FilmImage> findByImageName(String name);
    void deleteByImageName(String name);
    void deleteByListOfId(List<Long> list);
}
