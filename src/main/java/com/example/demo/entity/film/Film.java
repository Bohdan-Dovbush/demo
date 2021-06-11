package com.example.demo.entity.film;

import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate filmYear;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String mainImage;
    @URL
    private String trailerLink;
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate dateRelease;
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    private LocalDate dateFinish;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_images", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "images")
    private Set<String> filmImages;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seos_id")
    private Seo seo;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Actor> filmActor = new ArrayList<>();

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

    public void addFilmActor(Actor actor){
        filmActor.add(actor);
        actor.setFilm(this);
    }

    public String getMainImagePath() {
        if(filmId == null || mainImage == null) return null;
        return "/uploads/" + mainImage;
    }
}
