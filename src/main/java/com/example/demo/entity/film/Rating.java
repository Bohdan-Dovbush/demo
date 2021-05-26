package com.example.demo.entity.film;

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
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;
    private Integer rating; // How much stars
    private Integer evaluation; // Vote stars

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    public Rating(Integer rating, Integer evaluation) {
        this.rating = rating;
        this.evaluation = evaluation;
    }

    public Rating(Integer rating, Integer evaluation, Film film) {
        this.rating = rating;
        this.evaluation = evaluation;
        this.film = film;
    }

    public Rating(Integer rating, Integer evaluation, Details details) {
        this.rating = rating;
        this.evaluation = evaluation;
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating = (Rating) o;
        return Objects.equals(ratingId, rating.ratingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId);
    }
}
