package com.example.demo.entity.film;

import com.example.demo.entity.gallery.CinemaImage;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;
    @NotNull(message = "please put some name")
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

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CinemaImage> cinemaImages = new ArrayList<>();

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Hall> halls = new ArrayList<>();

    public void addCinemaImage(CinemaImage image){
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
