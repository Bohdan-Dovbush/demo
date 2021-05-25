package com.example.demo.entity.gallery;

import com.example.demo.entity.film.Film;
import com.example.demo.entity.user.Details;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "film_id")
    private Set<Film> films;

    @OneToMany
    @JoinColumn(name = "details_id")
    private Set<Details> details;
}
