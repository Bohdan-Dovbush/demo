package com.example.demo.entity.booking;

import com.example.demo.entity.gallery.Image;
import com.example.demo.entity.user.Seo;
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

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hall_image",
            joinColumns = @JoinColumn(name = "hall_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> hallImages = new ArrayList<>();

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Place> hallPlaces = new ArrayList<>();

    @OneToMany(mappedBy = "hall",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Seance> seances = new ArrayList<>();

    public Hall(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Hall(String name, String description, String schemaImage, String bannerImage) {
        this.name = name;
        this.description = description;
        this.schemaImage = schemaImage;
        this.bannerImage = bannerImage;
    }

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
