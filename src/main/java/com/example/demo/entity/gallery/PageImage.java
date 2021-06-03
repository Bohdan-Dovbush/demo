package com.example.demo.entity.gallery;

import com.example.demo.entity.user.Page;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "page_image")
public class PageImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_image_id")
    private long pageImageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public PageImage(String image) {
        this.image = image;
    }
}
