package com.example.demo.entity.address;

import com.example.demo.entity.user.Contact;
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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    private String streetAddress;
    private Integer postalCode;
    private String region;

    @ManyToOne
    private City city;

    @ManyToOne
    private Country country;

    @OneToMany
    @JoinColumn(name = "contact_id")
    private Set<Contact> contacts;
}
