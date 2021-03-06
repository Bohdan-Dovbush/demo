package com.example.demo.entity.user;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String token;
    private Boolean accountVerified;
    private Integer failedLoginAttempts;
    private Boolean loginDisabled;

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_groups",
            joinColumns =@JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> userGroups = new HashSet<>();

    public void addUserGroups(Group group){
        userGroups.add(group);
        group.getUsers().add(this);
    }

    public void removeUserGroups(Group group){
        userGroups.remove(group);
        group.getUsers().remove(this);
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public boolean isLoginDisabled() {
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
