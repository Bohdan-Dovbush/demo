package com.example.demo.entity.gallery;

import com.example.demo.entity.user.Promotion;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion_image")
public class PromotionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_image_id")
    private long promotionImageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public PromotionImage(String image) {
        this.image = image;
    }
}
