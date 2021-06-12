package com.example.demo.entity.user;

import com.example.demo.entity.film.Seo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
    private LocalDate publishDate;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String videoLink;
    private Boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "promotion_images", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "images")
    private Set<String> promotionImages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @Transient
    public String getMainImagePath() {
        if(promotionId == null || mainImage == null) return null;
        return "/uploads/" + mainImage;
    }
}
