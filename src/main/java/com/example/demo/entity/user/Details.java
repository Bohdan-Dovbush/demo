package com.example.demo.entity.user;

import com.example.demo.entity.film.Rating;
import com.example.demo.entity.gallery.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<Image> detailsImage = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_gender",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private List<Gender> detailsGender = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_rating",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private List<Rating> detailsRating = new ArrayList<>();

    public Details(String firstName, String lastName, String language, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.birthday = birthday;
    }

    public Details(String firstName, String lastName, String language, LocalDate birthday, UserEntity users, List<Image> detailsImage, List<Gender> detailsGender, List<Rating> detailsRating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.birthday = birthday;
        this.users = users;
        this.detailsImage = detailsImage;
        this.detailsGender = detailsGender;
        this.detailsRating = detailsRating;
    }
}
