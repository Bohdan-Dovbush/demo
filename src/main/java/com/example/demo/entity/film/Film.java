package com.example.demo.entity.film;

import com.example.demo.entity.gallery.Image;
import com.example.demo.entity.user.Seo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    private String name;
    private LocalDate filmYear;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    private String trailerLink;
    private LocalDate dateRelease;
    private LocalDate dateFinish;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seos_id")
    private Seo seo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_image",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> filmImage = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> filmActor = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> filmCategory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> filmGenre = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_rating",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private List<Rating> filmRating = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "film_type",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> filmType = new ArrayList<>();

    public Film(String name, LocalDate filmYear, String description, String mainImage, String trailerLink, LocalDate dateRelease, LocalDate dateFinish, Seo seo) {
        this.name = name;
        this.filmYear = filmYear;
        this.description = description;
        this.mainImage = mainImage;
        this.trailerLink = trailerLink;
        this.dateRelease = dateRelease;
        this.dateFinish = dateFinish;
        this.seo = seo;
    }

    public Film(String name, LocalDate filmYear, String description, String mainImage, String trailerLink, LocalDate dateRelease, LocalDate dateFinish) {
        this.name = name;
        this.filmYear = filmYear;
        this.description = description;
        this.mainImage = mainImage;
        this.trailerLink = trailerLink;
        this.dateRelease = dateRelease;
        this.dateFinish = dateFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(filmId, film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }
}
