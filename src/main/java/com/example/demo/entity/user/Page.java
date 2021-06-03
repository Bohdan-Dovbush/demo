package com.example.demo.entity.user;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.gallery.PageImage;
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
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long pageId;
    private String name;
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PageImage> images = new ArrayList<>();

    public void addPageImage(PageImage pageImage){
        images.add(pageImage);
        pageImage.setPage(this);
    }
}
