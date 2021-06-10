package com.example.demo.entity.gallery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Image {

    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image_gallery", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "images_gallery")
    private Set<String> imagesGallery;

    public Image(Set<String> imagesGallery) {
        this.imagesGallery = imagesGallery;
    }
}
