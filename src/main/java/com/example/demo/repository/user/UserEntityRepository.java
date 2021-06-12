package com.example.demo.repository.user;

import com.example.demo.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(final String email);
    @Query(value = "SELECT COUNT(*) FROM user",nativeQuery = true)
    Integer findCountUser();
}
