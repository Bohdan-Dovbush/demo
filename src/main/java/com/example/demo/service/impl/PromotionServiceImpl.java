package com.example.demo.service.impl;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.Promotion;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.interfaces.PromotionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PromotionServiceImpl extends ImageServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Optional<Promotion> findWithImagesById(Long promotionId) {
        return promotionRepository.findWithImagesById(promotionId);
    }

    @Override
    public void updatePromotion(Long promotionId,
                                String promotionName,
                                String promotionDescription,
                                String promotionVideoLink,
                                Boolean promotionStatus,
                                LocalDate promotionPublishDate,
                                MultipartFile promotionMainImage,
                                MultipartFile[] promotionImages,
                                Seo seo) {
        findWithImagesById(promotionId).ifPresent(promotion -> {
            promotion.setName(promotionName);
            promotion.setDescription(promotionDescription);
            promotion.setActive(promotionStatus);
            promotion.setPublishDate(promotionPublishDate);
            promotion.setVideoLink(promotionVideoLink);
            checkPromotionImage(promotion, promotionMainImage, promotionImages);

            if (!promotion.getSeo().equals(seo)){
                promotion.setSeo(seo);
            }
            promotionRepository.saveAndFlush(promotion);
        });
    }

    @Override
    public void createPromotion(String promotionName,
                                String promotionDescription,
                                String promotionVideoLink,
                                Boolean promotionStatus,
                                LocalDate promotionPublishDate,
                                MultipartFile promotionMainImage,
                                MultipartFile[] promotionImages,
                                Seo seo) {
        Promotion promotion = new Promotion();
        promotion.setName(promotionName);
        promotion.setDescription(promotionDescription);
        promotion.setActive(promotionStatus);
        promotion.setPublishDate(promotionPublishDate);
        promotion.setVideoLink(promotionVideoLink);
        promotion.setSeo(seo);
        checkPromotionImage(promotion, promotionMainImage, promotionImages);
        promotionRepository.save(promotion);
    }

    @Override
    public void deleteById(Long promotionId) {
        Promotion promotion = new Promotion();

        deleteImage(promotion.getMainImage());
        deleteImageSet(promotion.getPromotionImages());
        promotionRepository.deleteById(promotionId);
    }

    void checkPromotionImage(Promotion promotion, MultipartFile mainImage, MultipartFile[] images) {
        if (!(mainImage == null) && !mainImage.isEmpty()){
            deleteImage(promotion.getMainImage());
            promotion.setMainImage(saveImage(mainImage));
        }
        if (!(images == null) && !(images.length == 0)) {
            if(!(images[0] == null) && !(images[0].isEmpty())) {
                deleteImageSet(promotion.getPromotionImages());
                promotion.setPromotionImages(saveImageArray(images));
            }
        }
    }
}
