package com.example.demo.entity.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "secure_token")
public class SecureToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timeStamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity user;

    @Transient
    private boolean isExpired;

    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }
}
