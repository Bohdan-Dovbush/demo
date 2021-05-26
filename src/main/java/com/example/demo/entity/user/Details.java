package com.example.demo.entity.user;

import com.example.demo.entity.film.Rating;
import com.example.demo.entity.gallery.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    private Long detailsId;
    private String firstName;
    private String lastName;
    private String language;
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_image",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> detailsImage = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_gender",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private Set<Gender> detailsGender = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_rating",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private Set<Rating> detailsRating = new HashSet<>();
}
