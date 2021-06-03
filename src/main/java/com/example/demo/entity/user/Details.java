package com.example.demo.entity.user;

import com.example.demo.entity.enums.Gender;
import com.example.demo.entity.enums.Language;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    private Long detailsId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Details)) return false;
        Details details = (Details) o;
        return Objects.equals(detailsId, details.detailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailsId);
    }
}
