package com.example.demo.repository;

import com.example.demo.entity.user.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query(value = "SELECT * FROM promotion as p LEFT JOIN promotion_images as pi on p.promotion_id = pi.promotion_id where p.promotion_id = ?1", nativeQuery = true)
    Optional<Promotion> findWithImagesById(Long promotionId);
}
