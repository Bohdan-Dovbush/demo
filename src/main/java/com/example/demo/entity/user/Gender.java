package com.example.demo.entity.user;

import com.example.demo.entity.film.Actor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genderId;
    private String name;
    @OneToMany(mappedBy = "actorGender")
    private List<Actor> actorList;
    @OneToMany(mappedBy = "userDetailsGender")
    private List<UserDetails> userDetailsList;

}
