package com.example.demo.entity.user;
import com.example.demo.entity.address.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;
    @Column(length = 9)
    private String phone;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "contact_address",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> contactAddress = new HashSet<>();

    public void addContactAddress(Address address) {
        contactAddress.add(address);
        address.getContacts().add(this);
    }
}
