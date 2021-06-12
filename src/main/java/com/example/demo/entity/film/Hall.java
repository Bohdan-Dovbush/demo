package com.example.demo.entity.film;

import com.example.demo.entity.booking.Place;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hall_images", joinColumns = @JoinColumn(name = "hall_id"))
    @Column(name = "images")
    private Set<String> hallImages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private List<Place> hallPlaces = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private List<Seance> seances = new ArrayList<>();

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

    @Transient
    public String getMainImagePath() {
        if(hallId == null || schemaImage == null) return null;
        return "/uploads/" + schemaImage;
    }
}
