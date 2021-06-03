package com.example.demo.entity.gallery;

import com.example.demo.entity.film.Hall;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hall_image")
public class HallImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_image_id")
    private Long hallImageId;
    private String image;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public HallImage(String image) {
        this.image = image;
    }
}
