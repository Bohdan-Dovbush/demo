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
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;
    private String name;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDate publishDate;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String videoLink;
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "news_images", joinColumns = @JoinColumn(name = "news_id"))
    @Column(name = "images")
    private Set<String> newsImages;

    @Transient
    public String getMainImagePath() {
        if(newsId == null || mainImage == null) return null;
        return "/uploads/" + mainImage;
    }
}
