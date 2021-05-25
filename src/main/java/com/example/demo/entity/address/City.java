package com.example.demo.entity.address;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;
    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany
    @JoinColumn(name = "address_id")
    private Set<Address> addresses;
}
