package com.example.demo.service.interfaces;

import com.example.demo.entity.enums.Gender;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.user.Details;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DetailsService {

    Optional<Details> findById(Long id);
    List<Details> findAll();
    void updateDetails(Long detailsId,
                       LocalDate birthday,
                       MultipartFile mainImage,
                       Language language,
                       Gender gender);
}
