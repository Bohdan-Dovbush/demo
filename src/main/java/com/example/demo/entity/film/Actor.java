package com.example.demo.entity.film;

import com.example.demo.entity.user.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;
    private String firstName;
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "actor_gender",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private List<Gender> actorGender = new ArrayList<>();

    public Actor(String firstName, String lastName, List<Gender> actorGender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.actorGender = actorGender;
    }

    public Actor(String firstName, String lastName, Film film, List<Gender> actorGender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.film = film;
        this.actorGender = actorGender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(actorId, actor.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }
}
