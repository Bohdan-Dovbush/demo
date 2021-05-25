package com.example.demo.entity.user;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "role_type")
public class Group{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;
    private String name;

    @ManyToMany(mappedBy = "userGroups")
    private Set<UserEntity> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group userGroup = (Group) o;
        return Objects.equals(id, userGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
