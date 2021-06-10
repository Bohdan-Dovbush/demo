package com.example.demo.entity.film;

import com.example.demo.entity.gallery.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cinema")
public class Cinema extends Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;
    private String name;
    private Boolean active;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String rules;
    private String mainImage;
    private String logoImage;
    private String upperBannerImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seos_id")
    private Seo seo;

//    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<CinemaImage> cinemaImages = new ArrayList<>();

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Hall> halls = new ArrayList<>();

//    public void addCinemaImage(CinemaImage image){
//        cinemaImages.add(image);
//        image.setCinema(this);
//    }

    public void addCinemaHall(Hall hall){
        halls.add(hall);
        hall.setCinema(this);
    }

    public Cinema(Set<String> imagesGallery, String name, Boolean active, String description,
                  String rules, String mainImage, String logoImage,
                  String upperBannerImage, Seo seo) {
        super(imagesGallery);
        this.name = name;
        this.active = active;
        this.description = description;
        this.rules = rules;
        this.mainImage = mainImage;
        this.logoImage = logoImage;
        this.upperBannerImage = upperBannerImage;
        this.seo = seo;
    }

    public Boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema)) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(cinemaId, cinema.cinemaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaId);
    }
}
