package com.example.demo.entity.booking;

import com.example.demo.entity.gallery.Image;
import com.example.demo.entity.film.Seo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
@Table(name = "cinema")
public class Cinema {

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private List<Image> cinemaImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private List<Hall> halls = new ArrayList<>();

    public void addCinemaImage(Image image){
        cinemaImages.add(image);
        image.setCinema(this);
    }

    public void addCinemaHall(Hall hall){
        halls.add(hall);
        hall.setCinema(this);
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
