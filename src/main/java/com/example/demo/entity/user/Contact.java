//package com.example.demo.entity.user;
//import com.example.demo.entity.address.Country;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Data
//@Table(name = "contact")
//public class Contact {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "contact_id")
//    private Long contactId;
//    @Column(length = 9)
//    private Integer phone;
//    @Column(length = 9)
//    private Integer secondPhone;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "country_id")
//    private Country country;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Contact)) return false;
//        Contact contact = (Contact) o;
//        return Objects.equals(contactId, contact.contactId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(contactId);
//    }
//}
