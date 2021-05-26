package com.example.demo.entity.gallery;

import com.example.demo.entity.film.Film;
import com.example.demo.entity.user.Details;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "image_id")
    private Long imageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film films;

    @ManyToOne
    @JoinColumn(name = "details_id")
    private Details details;
}
