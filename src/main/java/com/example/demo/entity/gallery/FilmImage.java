package com.example.demo.entity.gallery;

import com.example.demo.entity.film.Film;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film_image")
public class FilmImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_image_id")
    private Long filmImageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public FilmImage(String image) {
        this.image = image;
    }
}
