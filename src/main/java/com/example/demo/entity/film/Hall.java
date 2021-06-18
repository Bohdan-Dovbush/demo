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
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long hallId;
    private String name;
    @Column(columnDefinition = "text", length = 2000)
    private String description;
    private Integer countTicket;
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

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Seance> seances = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Place> places = new ArrayList<>();

    public Hall(String name, String description, Integer countTicket) {
        this.name = name;
        this.description = description;
        this.countTicket = countTicket;
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

    @Transient
    public String getSchemaImagePath() {
        if(hallId == null || schemaImage == null) return null;
        return "/uploads/" + schemaImage;
    }

    @Transient
    public String getBannerImagePath() {
        if(hallId == null || bannerImage == null) return null;
        return "/uploads/" + bannerImage;
    }
}
