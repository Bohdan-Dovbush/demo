package com.example.demo.entity.gallery;

import com.example.demo.entity.booking.Cinema;
import com.example.demo.entity.booking.Hall;
import com.example.demo.entity.film.Film;
import com.example.demo.entity.user.Details;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film films;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details detail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Image(String image) {
        this.image = image;
    }

    public Image(String image, Film films) {
        this.image = image;
        this.films = films;
    }

    public Image(String image, Hall hall) {
        this.image = image;
        this.hall = hall;
    }

    public Image(String image, Cinema cinema) {
        this.image = image;
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
