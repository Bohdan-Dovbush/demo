package com.example.demo.entity.gallery;

import com.example.demo.entity.booking.Cinema;
import com.example.demo.entity.booking.Hall;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.user.Details;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    private String image;
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film films;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Image(String image, String url) {
        this.image = image;
        this.url = url;
    }

    public Image(String image, String url, Film films) {
        this.image = image;
        this.url = url;
        this.films = films;
    }

    public Image(String image, String url, Hall hall) {
        this.image = image;
        this.url = url;
        this.hall = hall;
    }

    public Image(String image, String url, Cinema cinema) {
        this.image = image;
        this.url = url;
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId);
    }
}
