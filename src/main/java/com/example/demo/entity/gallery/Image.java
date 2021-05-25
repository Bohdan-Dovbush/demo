package com.example.demo.entity.gallery;

import com.example.demo.entity.film.Film;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
