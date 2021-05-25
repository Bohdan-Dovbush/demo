package com.example.demo.entity.film;

import com.example.demo.entity.user.Details;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<Film> film;

    @OneToMany
    @JoinColumn(name = "details_id")
    private Set<Details> details;
}
