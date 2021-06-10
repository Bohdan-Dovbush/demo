package com.example.demo.entity.user;

import com.example.demo.entity.film.Seo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "page_images", joinColumns = @JoinColumn(name = "page_id"))
    @Column(name = "images")
    private Set<String> pageImages;
}
