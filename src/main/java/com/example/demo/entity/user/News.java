package com.example.demo.entity.user;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.NewsImage;
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
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;
    private String name;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime publishDate;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String videoLink;
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @OneToMany (mappedBy = "news", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NewsImage> newsImages = new ArrayList<>();

    public void addNewsImage(NewsImage image) {
        newsImages.add(image);
        image.setNews(this);
    }
}
