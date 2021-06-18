package com.example.demo.service.impl;

import com.example.demo.entity.enums.Gender;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.user.Details;
import com.example.demo.repository.user.DetailsRepository;
import com.example.demo.service.interfaces.DetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetailsServiceImpl extends ImageServiceImpl implements DetailsService {

    private final DetailsRepository detailsRepository;

    public DetailsServiceImpl(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    @Override
    public Optional<Details> findById(Long id) {
        return detailsRepository.findById(id);
    }

    @Override
    public List<Details> findAll() {
        return detailsRepository.findAll();
    }

    @Override
    public void updateDetails(Long detailsId,
                              LocalDate birthday,
                              MultipartFile mainImage,
                              Language language,
                              Gender gender) {
        findById(detailsId).ifPresent(details -> {
            details.setBirthday(birthday);
            details.setGender(gender);
            details.setLanguage(language);
            if (!(mainImage == null) && !(mainImage.isEmpty())) {
                deleteImage(details.getAvatar());
                details.setAvatar(saveImage(mainImage));
            }
            detailsRepository.saveAndFlush(details);
        });
    }
}
