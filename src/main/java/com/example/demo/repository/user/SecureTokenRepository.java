package com.example.demo.repository.user;

import com.example.demo.entity.user.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(final String token);
    Long removeByToken(final String token);
}
