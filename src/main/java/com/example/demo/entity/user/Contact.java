package com.example.demo.entity.user;
import com.example.demo.entity.address.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contact_address",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> contactAddress = new ArrayList<>();

    public Contact(String phone, UserEntity users, List<Address> contactAddress) {
        this.phone = phone;
        this.users = users;
        this.contactAddress = contactAddress;
    }
}
