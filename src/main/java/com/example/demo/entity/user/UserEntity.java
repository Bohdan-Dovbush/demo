package com.example.demo.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "Please enter the correct email")
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String token;
    private Boolean accountVerified;
    private Integer failedLoginAttempts;
    private Boolean loginDisabled;

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id")
    private Details details;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_groups",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Group> userGroups = new HashSet<>();

    public void addUserGroups(Group group) {
        userGroups.add(group);
        group.getUsers().add(this);
    }

    public Boolean isAccountVerified() {
        return accountVerified;
    }

    public Boolean isLoginDisabled() {
        return loginDisabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
