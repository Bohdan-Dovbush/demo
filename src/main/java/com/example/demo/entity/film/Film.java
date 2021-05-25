package com.example.demo.entity.film;

import com.example.demo.entity.gallery.Image;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;
    private String name;
    private LocalDate filmYear;
    private LocalTime filmTime;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String trailerLink;
    private LocalDate dateRelease;
    private LocalDate dateFinish;

    @OneToMany(mappedBy = "filmImage", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "film_image",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "filmActor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList = new ArrayList<>();

    @OneToMany(mappedBy = "filmCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "filmGenre", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList = new ArrayList<>();

    @OneToMany(mappedBy = "filmRating", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "film_rating",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private List<Rating> ratingList = new ArrayList<>();
}
