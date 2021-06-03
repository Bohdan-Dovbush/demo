package com.example.demo.entity.gallery;

import com.example.demo.entity.user.News;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news_image")
public class NewsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_image_id")
    private Long newsImageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    public NewsImage(String image) {
        this.image = image;
    }
}
