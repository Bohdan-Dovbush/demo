package com.example.demo.entity.user;

import com.example.demo.entity.film.Actor;
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

}
