package com.example.demo.entity.film;

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
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<Film> film;
}
