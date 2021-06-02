package com.example.demo.entity.booking;

import com.example.demo.entity.gallery.Image;
import com.example.demo.entity.film.Seo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long hallId;
    private String name;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private String schemaImage;
    private String bannerImage;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seos_id")
    private Seo seo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private List<Image> hallImages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private List<Place> hallPlaces = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private List<Seance> seances = new ArrayList<>();

    public void addHallImage(Image image){
        hallImages.add(image);
        image.setHall(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        Hall hall = (Hall) o;
        return Objects.equals(hallId, hall.hallId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallId);
    }
}
