package com.example.demo.entity.user;

import com.example.demo.entity.film.Actor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long genderId;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id")
    private Actor actors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    public Gender(String name) {
        this.name = name;
    }

    public Gender(String name, Actor actors) {
        this.name = name;
        this.actors = actors;
    }

    public Gender(String name, Details details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gender)) return false;
        Gender gender = (Gender) o;
        return Objects.equals(genderId, gender.genderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genderId);
    }
}
