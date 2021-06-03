package com.example.demo.entity.user;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.PromotionImage;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long promotionId;
    private String name;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime publishDate;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String videoLink;
    private Boolean active;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PromotionImage> promotionImages = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    public void addPromotionImage(PromotionImage image){
        promotionImages.add(image);
        image.setPromotion(this);
    }
}
